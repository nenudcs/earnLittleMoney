drop table if exists t_talk;

/*==============================================================*/
/* Table: t_talk                                                */
/*==============================================================*/
create table t_talk
(
   id                   int not null auto_increment,
   candidate_id         int,
   judge_id             int,
   score_1              double,  /* 理论素养，30 */
   score_2              double,  /* 沟通技巧，20 */
   score_3              double,  /* 教育效果，30 */
   score_4              double,  /* 知识运用，20 */
   primary key (id)
);
