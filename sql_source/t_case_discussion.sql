drop table if exists t_case_discussion;

/*==============================================================*/
/* Table: t_case_discussion                                     */
/*==============================================================*/
create table t_case_discussion
(
   id                   int not null auto_increment,
   candidate_id         int,
   judge_id             int,
   score_1              double,  /* 提问分数，满分100分 */
   score_2              double,  /* 作答分数，满分100分 */
   primary key (id)
);
