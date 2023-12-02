drop table if exists t_judges;

/*==============================================================*/
/* Table: t_judges                                              */
/*==============================================================*/
create table t_judges
(
   id                   int not null auto_increment,
   name                 varchar(255),
   password             varchar(255),
   primary key (id)
);
