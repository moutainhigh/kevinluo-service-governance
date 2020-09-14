-- ---------------------------------
-- 注册服务的列表信息
-- ---------------------------------
drop table if exists governance_server;
create table governance_server (
    `id` varchar(32) not null comment 'id',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp
     on update current_timestamp comment '更新时间',
     `server_name` varchar(255) not null comment '服务名称',
     `open_api` varchar (64) not null comment '开放API地址',
     `management` int (11)  not null comment '管理页面',
     `version` varchar (255) not null comment '当前版本',
     `pack` varchar(12) not null comment '当前版本的包',
     `upgrade_time` timestamp not null default current_timestamp comment '创建时间',
);
