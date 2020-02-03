package com.splan.bplan.mappers;

import com.alibaba.fastjson.JSONObject;
import com.splan.base.base.SuperMapper;
import com.splan.base.bean.SysPermission;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Set;

public interface SysPermissionMapper extends SuperMapper<SysPermission> {

    @Select("SELECT\n" +
            "            u.id              userId,\n" +
            "            u.nickname        nickname,\n" +
            "            u.role_id         roleId,\n" +
            "            r.role_name       roleName,\n" +
            "            p.menu_code       menuCode,\n" +
            "            p.permission_code permissionCode\n" +
            "        FROM sys_user u\n" +
            "                 LEFT JOIN sys_role r ON r.id = u.role_id\n" +
            "                 LEFT JOIN sys_role_permission rp ON u.role_id = rp.role_id\n" +
            "                 LEFT JOIN sys_permission p ON rp.permission_id = p.id AND rp.delete_status = '1'\n" +
            "        WHERE u.username = #{username}\n" +
            "          AND u.delete_status = '1'")
    JSONObject getUserPermission(String username);

    @Select("select t.role_id as roleId,t.api_id as apiid,t.fromid as fromid from sys_user t where t.delete_status='1' and t.username=#{username}")
    JSONObject getUserRoleId(String username);

    @Select("SELECT p.menu_code menuCode\n" +
            "        FROM sys_permission p\n" +
            "        ORDER BY p.id")
    Set<String> getAllMenu();

    @Select("SELECT p.permission_code permissionCode\n" +
            "        FROM sys_permission p\n" +
            "        ORDER BY p.id")
    Set<String> getAllPermission();

    @Select("SELECT\n" +
            "            p.menu_code       menuCode\n" +
            "        FROM sys_user u\n" +
            "                 LEFT JOIN sys_role r ON r.id = u.role_id\n" +
            "                 LEFT JOIN sys_role_permission rp ON u.role_id = rp.role_id\n" +
            "                 LEFT JOIN sys_permission p ON rp.permission_id = p.id AND rp.delete_status = '1'\n" +
            "        WHERE u.username = #{username}\n" +
            "          AND u.delete_status = '1'")
    Set<String> getMenuByusername(String username);

    @Select("SELECT\n" +
            "            p.permission_code permissionCode\n" +
            "        FROM sys_user u\n" +
            "                 LEFT JOIN sys_role r ON r.id = u.role_id\n" +
            "                 LEFT JOIN sys_role_permission rp ON u.role_id = rp.role_id\n" +
            "                 LEFT JOIN sys_permission p ON rp.permission_id = p.id AND rp.delete_status = '1'\n" +
            "        WHERE u.username = #{username}\n" +
            "          AND u.delete_status = '1'")
    Set<String> getPermissionByusernamme(String username);

    @Select("select p.* from sys_user u " +
            "left join sys_role r on r.id = u.role_id " +
            "left join sys_role_permission rp on u.role_id = rp.role_id " +
            "left join sys_permission p on rp.permission_id = p.id and rp.delete_status = '1' " +
            "where u.id = #{userId}")
    List<SysPermission> getUserPermissions(@Param("userId")Integer userId);

    @Select("select * from sys_permission")
    List<SysPermission> getAllPermissions();

    @Select("select p.*,u.id as uid,u.username as uname,u.nickname as unickname,r.role_name as rolename,r.role_id as rid from sys_user u " +
            "left join sys_role r on r.id = u.role_id " +
            "left join sys_role_permission rp on u.role_id = rp.role_id " +
            "left join sys_permission p on rp.permission_id = p.id and rp.delete_status = '1' " +
            "where u.id = #{userId}")
    List<SysPermission> getUserPermissions2(@Param("userId")Integer userId);

}