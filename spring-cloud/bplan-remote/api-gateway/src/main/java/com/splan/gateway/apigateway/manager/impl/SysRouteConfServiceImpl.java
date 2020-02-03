package com.splan.gateway.apigateway.manager.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.splan.gateway.apigateway.bean.RouteDefinitionVo;
import com.splan.gateway.apigateway.bean.SysRouteConf;
import com.splan.gateway.apigateway.constant.CommonConstants;
import com.splan.gateway.apigateway.manager.SysRouteConfService;
import com.splan.gateway.apigateway.mapper.SysRouteConfMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
//@AllArgsConstructor
@Service("sysRouteConfService")
public class SysRouteConfServiceImpl extends ServiceImpl<SysRouteConfMapper, SysRouteConf> implements SysRouteConfService {

    //@Autowired
    @Resource
    private RedisTemplate<String,Object> cloudRedisTemplate;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;


    /**
     * 获取全部路由
     * <p>
     * RedisRouteDefinitionWriter.java
     * PropertiesRouteDefinitionLocator.java
     *
     * @return
     */
    @Override
    public List<SysRouteConf> routes() {
        return baseMapper.selectList(Wrappers.emptyWrapper());
    }

    /**
     * 更新路由信息
     *
     * @param routes 路由信息
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Mono<Void> updateRoutes(JSONArray routes) {
        // 清空Redis 缓存
        Boolean result = cloudRedisTemplate.delete(CommonConstants.ROUTE_KEY);
        log.info("清空网关路由 {} ", result);

        // 遍历修改的routes，保存到Redis
        List<RouteDefinitionVo> routeDefinitionVoList = new ArrayList<>();
        routes.forEach(value -> {
            log.info("更新路由 ->{}", value);
            RouteDefinitionVo vo = new RouteDefinitionVo();
            Map<String, Object> map = (Map) value;

            Object id = map.get("routeId");
            if (id != null) {
                vo.setId(String.valueOf(id));
            }

            Object routeName = map.get("routeName");
            if (routeName != null) {
                vo.setRouteName(String.valueOf(routeName));
            }

            Object predicates = map.get("predicates");
            if (predicates != null) {
                JSONArray predicatesArray = (JSONArray) predicates;
                List<PredicateDefinition> predicateDefinitionList =
                        predicatesArray.toJavaList(PredicateDefinition.class);
                vo.setPredicates(predicateDefinitionList);
            }

            Object filters = map.get("filters");
            if (filters != null) {
                JSONArray filtersArray = (JSONArray) filters;
                List<FilterDefinition> filterDefinitionList
                        = filtersArray.toJavaList(FilterDefinition.class);
                vo.setFilters(filterDefinitionList);
            }

            Object uri = map.get("uri");
            if (uri != null) {
                vo.setUri(URI.create(String.valueOf(uri)));
            }

            Object order = map.get("order");
            if (order != null) {
                vo.setOrder(Integer.parseInt(String.valueOf(order)));
            }

            cloudRedisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(RouteDefinitionVo.class));
            cloudRedisTemplate.opsForHash().put(CommonConstants.ROUTE_KEY, vo.getId(), vo);
            routeDefinitionVoList.add(vo);
        });

        // 逻辑删除全部
        SysRouteConf condition = new SysRouteConf();
        condition.setDelFlag(CommonConstants.STATUS_NORMAL);
        this.remove(new UpdateWrapper<>(condition));

        //插入生效路由
        List<SysRouteConf> routeConfList = routeDefinitionVoList.stream().map(vo -> {
            SysRouteConf routeConf = new SysRouteConf();
            routeConf.setRouteId(vo.getId());
            routeConf.setRouteName(vo.getRouteName());
            routeConf.setFilters(JSON.toJSONString(vo.getFilters()));
            routeConf.setPredicates(JSON.toJSONString(vo.getPredicates()));
            routeConf.setOrder(vo.getOrder());
            routeConf.setUri(vo.getUri().toString());
            return routeConf;
        }).collect(Collectors.toList());
        this.saveBatch(routeConfList);
        log.debug("更新网关路由结束 ");

        this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
        return Mono.empty();
    }
}
