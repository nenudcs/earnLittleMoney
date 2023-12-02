drop table if exists t_candidate;

/*==============================================================*/
/* Table: t_candidate                                           */
/*==============================================================*/
create table t_candidate
(
   id                   int not null auto_increment,
   num                  int,  /* 序号 */
   name                 varchar(255),
   score_1              double, /* 基础知识分数 */
   score_2              double,  /* 案例讨论分数 */
   score_3              double,  /* 理论宣讲分数 */
   score_4              double,  /* 谈心谈话分数 */
   score_half           double,  /* 初赛分数 */
   score_total          double, /* 决赛分数 */
   hall_id              tinyint, /* 分会场id，0/1 */
   promote              tinyint, /* 是否晋级，0/1 */
   primary key (id)
);
