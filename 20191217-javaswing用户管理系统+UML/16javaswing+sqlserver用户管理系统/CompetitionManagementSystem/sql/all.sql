use master;

drop database if exists competition;
create database competition
on primary 
(name =competition_data,
filename=''F:\_idea\CompetitionManagementSystem\assets\competition_data.mdf'',
size=30mb,
maxsize=unlimited
),
(name=competition_data1,
filename=''F:\_idea\CompetitionManagementSystem\assets\competition_data.ndf'',
size=30mb,
maxsize=unlimited
)
log on
(name =competition_log,
filename=''F:\_idea\CompetitionManagementSystem\assets\competition_data.ldf'',
size=15mb,
maxsize=unlimited
)
use competition;

drop table if exists member;
drop table if exists grade;
drop table if exists team;
drop table if exists teacher;
drop table if exists production;
drop table if exists school_admin;
drop table if exists hoster_admin;

create table teacher
(
    id varchar(40) primary key,
    code varchar(30), --导师编号
    name varchar(50), --名字
    phone varchar(20), --手机号码
    email varchar(40), --邮箱
    title varchar(20), --职称
    department varchar(50), --所在院系
    school varchar(50), --所在学校
);

create table school_admin
(
    id varchar(40) primary key,
    code varchar(30), --编号
    name varchar(50), --名字
    school varchar(50), --所在学校,
    password varchar(40) default ''aaaaaa'', --密码
);

create table hoster_admin
(
    id varchar(40) primary key,
    code varchar(30), --编号
    name varchar(30), --名字
    school varchar(50), --所在学校,
    password varchar(40) default ''aaaaaa'', --密码
);

create table production
(
    id varchar(40) primary key,
    code varchar(30),
    name varchar(50),
    description text,
    path varchar(200)
);

create table team
(
    id varchar(40) primary key,
    code varchar(30),
    name varchar(50),
    group_in varchar(2) CHECK(group_in LIKE ''[A-D]''), --参赛组别
    school varchar(30),--院校名称
    school_admin_id varchar(40),
    teacher_id varchar(40),
    production_id varchar(40),
    foreign key (teacher_id) references teacher(id),
    foreign key (production_id) references production(id),
    foreign key (school_admin_id) references school_admin(id),
);

create table grade
(
    id varchar(40) primary key,
    team_id varchar(40),
    pgrade float check(pgrade>=0 and pgrade<=100), --初赛成绩
    lgrade float check(lgrade>=0 and lgrade<=100), --决赛成绩
    hoster_admin_id varchar(40) , --主办方终端管理员编号
    foreign key (team_id) references team(id),
    foreign key (hoster_admin_id) references hoster_admin(id),
);

create table member
(
    id char(40) primary key,
    id_card char(40), -- 身份证号
    name varchar(50), -- 姓名
    phone varchar(20), -- 手机号码
    email varchar(40), -- 邮箱
    sex varchar(2) check(sex =''男'' or sex = ''女''), -- 性别
    class varchar(20), --年级
    department varchar(50),--所在院系
    school varchar(50), --所在学校
    password varchar(40) default ''aaaaaa'', --密码
    team_id varchar(40),-- 队伍
    is_leader tinyint check(is_leader = 1 or is_leader=0), --队长?
    foreign key (team_id) references team(id),
);

insert into school_admin(id, code, name, school, password) values (''C12341'',''C12341'',''牛行'',''上海建桥学院'',''aaaaaa'');
insert into school_admin(id, code, name, school, password) values (''C12342'',''C12342'',''文宇通'',''上海复旦大学'',''aaaaaa'');
insert into school_admin(id, code, name, school, password) values (''C12343'',''C12343'',''包得知'',''上海大学'',''aaaaaa'');
insert into school_admin(id, code, name, school, password) values (''C12347'',''C12347'',''孙吉祥'',''上海政法大学'',''aaaaaa'');

insert into hoster_admin(id, code, name, school, password) values (''C12344'',''C12344'',''林镇'',''上海复旦大学'',''aaaaaa'');
insert into hoster_admin(id, code, name, school, password) values (''C12345'',''C12345'',''严厉'',''上海复旦大学'',''aaaaaa'');
insert into hoster_admin(id, code, name, school, password) values (''C12346'',''C12346'',''林镇'',''上海复旦大学'',''aaaaaa'');

insert into teacher(id, code, name, phone, email, title, department, school) values (''12834675'',''S18723'', ''张韶涵'',''12834675'',''12834675@gench.cn'',''教授'',  ''信息技术学院'',''上海建桥学院'');
insert into teacher(id, code, name, phone, email, title, department, school) values (''12354456'',''S12445'', ''林博学'',''12354456'',''12354456@gench.cn'',''讲师'',  ''信息技术学院'',''上海复旦大学'');
insert into teacher(id, code, name, phone, email, title, department, school) values (''18977667'',''S214112'',''袁世泽'',''18977667'',''18977667@gench.cn'',''教授'',  ''信息技术学院'',''上海大学''    );
insert into teacher(id, code, name, phone, email, title, department, school) values (''15657577'',''S124324'',''毛当''  ,''15657577'',''15657577@gench.cn'',''副教授'',''信息技术学院'',''上海政法大学'');

insert into production(id, code, name, description, path) values(''production1'',''production1'',''富强'',null,''1.png'');
insert into production(id, code, name, description, path) values(''production2'',''production2'',''民主'',null,''2.mp3'');
insert into production(id, code, name, description, path) values(''production3'',''production3'',''文明'',null,''3.mp4'');
insert into production(id, code, name, description, path) values(''production4'',''production4'',''和谐'',null,''4.jpg'');
insert into production(id, code, name, description, path) values(''production5'',''production5'',''自由'',null,''5.html'');
insert into production(id, code, name, description, path) values(''production6'',''production6'',''平等'',null,''6.jar'');

insert into team(id, code, name, group_in, school, school_admin_id, teacher_id, production_id) values(''team1'',''team1'',''富强队'',''A'',''上海建桥学院'',''C12341'',''12834675'',''production1'');
insert into team(id, code, name, group_in, school, school_admin_id, teacher_id, production_id) values(''team2'',''team2'',''民主队'',''A'',''上海复旦大学'',''C12342'',''12354456'',''production2'');
insert into team(id, code, name, group_in, school, school_admin_id, teacher_id, production_id) values(''team3'',''team3'',''文明队'',''A'',''上海大学'',    ''C12343'',''18977667'',''production3'');
insert into team(id, code, name, group_in, school, school_admin_id, teacher_id, production_id) values(''team4'',''team4'',''和谐队'',''A'',''上海政法大学'',''C12347'',''15657577'',''production4'');
insert into team(id, code, name, group_in, school, school_admin_id, teacher_id, production_id) values(''team5'',''team5'',''自由队'',''A'',''上海大学'',    ''C12343'',''12834675'',''production5'');
insert into team(id, code, name, group_in, school, school_admin_id, teacher_id, production_id) values(''team6'',''team6'',''平等队'',''A'',''上海大学'',    ''C12343'',''12354456'',''production6'');

insert into grade(id, team_id, pgrade, lgrade, hoster_admin_id) values(''1'',''team1'',''88'',''90'',''C12344'');
insert into grade(id, team_id, pgrade, lgrade, hoster_admin_id) values(''2'',''team2'',''70'',''0'',''C12345'');
insert into grade(id, team_id, pgrade, lgrade, hoster_admin_id) values(''3'',''team3'',''88'',''94'',''C12344'');
insert into grade(id, team_id, pgrade, lgrade, hoster_admin_id) values(''4'',''team4'',''85'',''91'',''C12346'');
insert into grade(id, team_id, pgrade, lgrade, hoster_admin_id) values(''5'',''team5'',''90'',''88'',''C12345'');
insert into grade(id, team_id, pgrade, lgrade, hoster_admin_id) values(''6'',''team6'',''86'',''85'',''C12346'');

insert into member(id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader) values (''2102873'',''2102873'',''李强'',  ''13320031'',''13320031@sina.com'',''男'',''大一'',''信息技术学院'',''上海建桥学院'',''aaaaaa'',''team1'', 1);
insert into member(id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader) values (''2039843'',''2039843'',''王大强'',''12003334'',''12003334@qq.com'',  ''男'',''大二'',''信息技术学院'',''上海复旦大学'',''aaaaaa'',''team2'', 1);
insert into member(id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader) values (''3002938'',''3002938'',''林小强'',''1223334'', ''1223334@sina.com'', ''女'',''大一'',''职业技术学院'',''上海大学''    ,''aaaaaa'',''team3'', 1);
insert into member(id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader) values (''1202932'',''1202932'',''薛碧柔'',''1588897'', ''1588897@qq.com'',   ''女'',''大二'',''信息技术学院'',''上海政法大学'',''aaaaaa'',''team4'', 1);
insert into member(id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader) values (''2346565'',''2346565'',''贾大亮'',''1234543'', ''1234543@126.com'',  ''男'',''大二'',''职业技术学院'',''上海大学''    ,''aaaaaa'',''team5'', 1);
insert into member(id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader) values (''1323456'',''1323456'',''唐唐'',  ''1988005'', ''1988005@126.com'',  ''女'',''大一'',''职业技术学院'',''上海大学''    ,''aaaaaa'',''team6'', 1);

insert into member(id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader) values (''4415232'',''4415232'',''王少伟'',  ''1986341'', ''1986341@sina.com'', ''男'',''大二'',''信息技术学院'',''上海建桥学院'',''aaaaaa'',''team1'', 0);
insert into member(id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader) values (''2319873'',''2319873'',''欧阳云海'',''12276654'',''12276654@qq.com'',  ''男'',''大二'',''信息技术学院'',''上海复旦大学'',''aaaaaa'',''team2'', 0);
insert into member(id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader) values (''3238723'',''3238723'',''王大明'',  ''1988644'', ''1988644@qq.com'',   ''男'',''大二'',''职业技术学院'',''上海大学''    ,''aaaaaa'',''team3'', 0);
insert into member(id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader) values (''2233412'',''2233412'',''彭莎'',    ''18898734'',''18898734@qq.com'',  ''女'',''大一'',''信息技术学院'',''上海政法大学'',''aaaaaa'',''team4'', 0);
insert into member(id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader) values (''4323423'',''4323423'',''关东'',    ''19983474'',''19983474@sina.com'',''女'',''大二'',''职业技术学院'',''上海大学''    ,''aaaaaa'',''team5'', 0);
insert into member(id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader) values (''2343543'',''2343543'',''唐唐'',    ''1223665'', ''1223665@126.com'',  ''女'',''大一'',''职业技术学院'',''上海大学''    ,''aaaaaa'',''team6'', 0);

insert into member(id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader) values (''3413434'',''3413434'',''林大大'',''1344531'', ''1344531@126.com'', ''男'',''大一'',''信息技术学院'',''上海建桥学院'',''aaaaaa'',''team1'', 0);
insert into member(id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader) values (''2335423'',''2335423'',''张梅里'',''1982742'', ''1982742@qq.com'',  ''男'',''大一'',''职业技术学院'',''上海复旦大学'',''aaaaaa'',''team2'', 0);
insert into member(id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader) values (''3543151'',''3543151'',''燕南栋'',''12887613'',''12887613@qq.com'', ''男'',''大一'',''职业技术学院'',''上海大学''    ,''aaaaaa'',''team3'', 0);
insert into member(id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader) values (''2432562'',''2432562'',''郭美琪'',''11344543'',''11344543@qq.com'', ''女'',''大一'',''信息技术学院'',''上海政法大学'',''aaaaaa'',''team4'', 0);
insert into member(id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader) values (''2351665'',''2351665'',''郑少华'',''19773242'',''19773242@126.com'',''男'',''大二'',''职业技术学院'',''上海大学''    ,''aaaaaa'',''team5'', 0);
insert into member(id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader) values (''3464456'',''3464456'',''张少少'',''1234996'', ''1234996@126.com'', ''女'',''大一'',''职业技术学院'',''上海大学''    ,''aaaaaa'',''team6'', 0);
