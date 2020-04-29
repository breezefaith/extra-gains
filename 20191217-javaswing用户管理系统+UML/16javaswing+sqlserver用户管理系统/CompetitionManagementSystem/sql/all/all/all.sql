use master;

drop database if exists competition;
create database competition
on primary 
(name =competition_data,
filename='F:\_idea\CompetitionManagementSystem\assets\competition_data.mdf',
size=30mb,
maxsize=unlimited
),
(name=competition_data1,
filename='F:\_idea\CompetitionManagementSystem\assets\competition_data.ndf',
size=30mb,
maxsize=unlimited
)
log on
(name =competition_log,
filename='F:\_idea\CompetitionManagementSystem\assets\competition_data.ldf',
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
    code varchar(30), --��ʦ���
    name varchar(50), --����
    phone varchar(20), --�ֻ�����
    email varchar(40), --����
    title varchar(20), --ְ��
    department varchar(50), --����Ժϵ
    school varchar(50), --����ѧУ
);

create table school_admin
(
    id varchar(40) primary key,
    code varchar(30), --���
    name varchar(50), --����
    school varchar(50), --����ѧУ,
    password varchar(40) default 'aaaaaa', --����
);

create table hoster_admin
(
    id varchar(40) primary key,
    code varchar(30), --���
    name varchar(30), --����
    school varchar(50), --����ѧУ,
    password varchar(40) default 'aaaaaa', --����
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
    group_in varchar(2) CHECK(group_in LIKE '[A-D]'), --�������
    school varchar(30),--ԺУ����
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
    pgrade float check(pgrade>=0 and pgrade<=100), --�����ɼ�
    lgrade float check(lgrade>=0 and lgrade<=100), --�����ɼ�
    hoster_admin_id varchar(40) , --���췽�ն˹���Ա���
    foreign key (team_id) references team(id),
    foreign key (hoster_admin_id) references hoster_admin(id),
);

create table member
(
    id char(40) primary key,
    id_card char(40), -- ���֤��
    name varchar(50), -- ����
    phone varchar(20), -- �ֻ�����
    email varchar(40), -- ����
    sex varchar(2) check(sex ='��' or sex = 'Ů'), -- �Ա�
    class varchar(20), --�꼶
    department varchar(50),--����Ժϵ
    school varchar(50), --����ѧУ
    password varchar(40) default 'aaaaaa', --����
    team_id varchar(40),-- ����
    is_leader tinyint check(is_leader = 1 or is_leader=0), --�ӳ�?
    foreign key (team_id) references team(id),
);

insert into school_admin(id, code, name, school, password) values ('C12341','C12341','ţ��','�Ϻ�����ѧԺ','aaaaaa');
insert into school_admin(id, code, name, school, password) values ('C12342','C12342','����ͨ','�Ϻ�������ѧ','aaaaaa');
insert into school_admin(id, code, name, school, password) values ('C12343','C12343','����֪','�Ϻ���ѧ','aaaaaa');
insert into school_admin(id, code, name, school, password) values ('C12347','C12347','�Ｊ��','�Ϻ�������ѧ','aaaaaa');

insert into hoster_admin(id, code, name, school, password) values ('C12344','C12344','����','�Ϻ�������ѧ','aaaaaa');
insert into hoster_admin(id, code, name, school, password) values ('C12345','C12345','����','�Ϻ�������ѧ','aaaaaa');
insert into hoster_admin(id, code, name, school, password) values ('C12346','C12346','����','�Ϻ�������ѧ','aaaaaa');

insert into teacher(id, code, name, phone, email, title, department, school) values ('12834675','S18723', '���غ�','12834675','12834675@gench.cn','����',  '��Ϣ����ѧԺ','�Ϻ�����ѧԺ');
insert into teacher(id, code, name, phone, email, title, department, school) values ('12354456','S12445', '�ֲ�ѧ','12354456','12354456@gench.cn','��ʦ',  '��Ϣ����ѧԺ','�Ϻ�������ѧ');
insert into teacher(id, code, name, phone, email, title, department, school) values ('18977667','S214112','Ԭ����','18977667','18977667@gench.cn','����',  '��Ϣ����ѧԺ','�Ϻ���ѧ'    );
insert into teacher(id, code, name, phone, email, title, department, school) values ('15657577','S124324','ë��'  ,'15657577','15657577@gench.cn','������','��Ϣ����ѧԺ','�Ϻ�������ѧ');

insert into production(id, code, name, description, path) values('production1','production1','��ǿ',null,'1.png');
insert into production(id, code, name, description, path) values('production2','production2','����',null,'2.mp3');
insert into production(id, code, name, description, path) values('production3','production3','����',null,'3.mp4');
insert into production(id, code, name, description, path) values('production4','production4','��г',null,'4.jpg');
insert into production(id, code, name, description, path) values('production5','production5','����',null,'5.html');
insert into production(id, code, name, description, path) values('production6','production6','ƽ��',null,'6.jar');

insert into team(id, code, name, group_in, school, school_admin_id, teacher_id, production_id) values('team1','team1','��ǿ��','A','�Ϻ�����ѧԺ','C12341','12834675','production1');
insert into team(id, code, name, group_in, school, school_admin_id, teacher_id, production_id) values('team2','team2','������','A','�Ϻ�������ѧ','C12342','12354456','production2');
insert into team(id, code, name, group_in, school, school_admin_id, teacher_id, production_id) values('team3','team3','������','A','�Ϻ���ѧ',    'C12343','18977667','production3');
insert into team(id, code, name, group_in, school, school_admin_id, teacher_id, production_id) values('team4','team4','��г��','A','�Ϻ�������ѧ','C12347','15657577','production4');
insert into team(id, code, name, group_in, school, school_admin_id, teacher_id, production_id) values('team5','team5','���ɶ�','A','�Ϻ���ѧ',    'C12343','12834675','production5');
insert into team(id, code, name, group_in, school, school_admin_id, teacher_id, production_id) values('team6','team6','ƽ�ȶ�','A','�Ϻ���ѧ',    'C12343','12354456','production6');

insert into grade(id, team_id, pgrade, lgrade, hoster_admin_id) values('1','team1','88','90','C12344');
insert into grade(id, team_id, pgrade, lgrade, hoster_admin_id) values('2','team2','70','0','C12345');
insert into grade(id, team_id, pgrade, lgrade, hoster_admin_id) values('3','team3','88','94','C12344');
insert into grade(id, team_id, pgrade, lgrade, hoster_admin_id) values('4','team4','85','91','C12346');
insert into grade(id, team_id, pgrade, lgrade, hoster_admin_id) values('5','team5','90','88','C12345');
insert into grade(id, team_id, pgrade, lgrade, hoster_admin_id) values('6','team6','86','85','C12346');

insert into member(id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader) values ('2102873','2102873','��ǿ',  '13320031','13320031@sina.com','��','��һ','��Ϣ����ѧԺ','�Ϻ�����ѧԺ','aaaaaa','team1', 1);
insert into member(id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader) values ('2039843','2039843','����ǿ','12003334','12003334@qq.com',  '��','���','��Ϣ����ѧԺ','�Ϻ�������ѧ','aaaaaa','team2', 1);
insert into member(id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader) values ('3002938','3002938','��Сǿ','1223334', '1223334@sina.com', 'Ů','��һ','ְҵ����ѧԺ','�Ϻ���ѧ'    ,'aaaaaa','team3', 1);
insert into member(id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader) values ('1202932','1202932','Ѧ����','1588897', '1588897@qq.com',   'Ů','���','��Ϣ����ѧԺ','�Ϻ�������ѧ','aaaaaa','team4', 1);
insert into member(id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader) values ('2346565','2346565','�ִ���','1234543', '1234543@126.com',  '��','���','ְҵ����ѧԺ','�Ϻ���ѧ'    ,'aaaaaa','team5', 1);
insert into member(id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader) values ('1323456','1323456','����',  '1988005', '1988005@126.com',  'Ů','��һ','ְҵ����ѧԺ','�Ϻ���ѧ'    ,'aaaaaa','team6', 1);

insert into member(id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader) values ('4415232','4415232','����ΰ',  '1986341', '1986341@sina.com', '��','���','��Ϣ����ѧԺ','�Ϻ�����ѧԺ','aaaaaa','team1', 0);
insert into member(id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader) values ('2319873','2319873','ŷ���ƺ�','12276654','12276654@qq.com',  '��','���','��Ϣ����ѧԺ','�Ϻ�������ѧ','aaaaaa','team2', 0);
insert into member(id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader) values ('3238723','3238723','������',  '1988644', '1988644@qq.com',   '��','���','ְҵ����ѧԺ','�Ϻ���ѧ'    ,'aaaaaa','team3', 0);
insert into member(id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader) values ('2233412','2233412','��ɯ',    '18898734','18898734@qq.com',  'Ů','��һ','��Ϣ����ѧԺ','�Ϻ�������ѧ','aaaaaa','team4', 0);
insert into member(id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader) values ('4323423','4323423','�ض�',    '19983474','19983474@sina.com','Ů','���','ְҵ����ѧԺ','�Ϻ���ѧ'    ,'aaaaaa','team5', 0);
insert into member(id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader) values ('2343543','2343543','����',    '1223665', '1223665@126.com',  'Ů','��һ','ְҵ����ѧԺ','�Ϻ���ѧ'    ,'aaaaaa','team6', 0);

insert into member(id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader) values ('3413434','3413434','�ִ��','1344531', '1344531@126.com', '��','��һ','��Ϣ����ѧԺ','�Ϻ�����ѧԺ','aaaaaa','team1', 0);
insert into member(id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader) values ('2335423','2335423','��÷��','1982742', '1982742@qq.com',  '��','��һ','ְҵ����ѧԺ','�Ϻ�������ѧ','aaaaaa','team2', 0);
insert into member(id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader) values ('3543151','3543151','���϶�','12887613','12887613@qq.com', '��','��һ','ְҵ����ѧԺ','�Ϻ���ѧ'    ,'aaaaaa','team3', 0);
insert into member(id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader) values ('2432562','2432562','������','11344543','11344543@qq.com', 'Ů','��һ','��Ϣ����ѧԺ','�Ϻ�������ѧ','aaaaaa','team4', 0);
insert into member(id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader) values ('2351665','2351665','֣�ٻ�','19773242','19773242@126.com','��','���','ְҵ����ѧԺ','�Ϻ���ѧ'    ,'aaaaaa','team5', 0);
insert into member(id, id_card, name, phone, email, sex, class, department, school, password, team_id, is_leader) values ('3464456','3464456','������','1234996', '1234996@126.com', 'Ů','��һ','ְҵ����ѧԺ','�Ϻ���ѧ'    ,'aaaaaa','team6', 0);
