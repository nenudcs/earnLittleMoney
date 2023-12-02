drop table if exists t_theoretical_presentation;

/*==============================================================*/
/* Table: t_theoretical_presentation                            */
/*==============================================================*/
create table t_theoretical_presentation
(
   id                   int not null auto_increment,
   candidate_id         int,
   judge_id             int,
   score_1              double,  /* 理论阐释，30 */
   score_2              double,  /* 宣讲技巧，20 */
   score_3              double,  /* 宣讲效果，20 */
   score_4              double,  /* 问题回答，20 */
   score_5              double,  /* 宣讲表现，10 */
   primary key (id)
);
