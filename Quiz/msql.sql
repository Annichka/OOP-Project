CREATE SCHEMA WebData;
USE WebData;

CREATE TABLE Quizes (
	quiz_id int(4) not null auto_increment primary key,
    quiz_name varchar(300) not null,
    author_id int(4) not null, 
    description text,
    category varchar(100) not null,
    isRandom int(1) not null,
    multiPage int(1) not null,
    correction int(1) not null,
    practice int(1) not null,
    finished int(1) not null,
    filled int(5)
);

CREATE TABLE Questions(
	id int(5) not null auto_increment primary key,
    question varchar(200) not null,
    c_answer text not null,
    w_answer text,
    answer_count int(2) not null,
    pic_url varchar(3000),
    quiz_id int(4) not null,
    type text not null,
    ordered int(1),
    constraint foreign key (quiz_id) references Quizes(quiz_id)
);

/* Extension: რამდენი ქვიზი აქვს შექმნილი/გაკეთებული, Achivements და Practice Mode */
CREATE TABLE Users (
	user_id int(4) not null auto_increment primary key,
    username varchar(20) not null,
    pass varchar (100) not null,
    e_mail varchar(50) not null,
    pic_url varchar(3000),
    deleted int(1)
);

CREATE TABLE Friends (
	id int(4) not null auto_increment primary key,
	user_id int(4) not null,
    friend_id int(4) not null,
    constraint foreign key (user_id) references Users(user_id)
);

CREATE TABLE Messages (
	id int(6) not null auto_increment primary key,
	u_from int(4) not null,
    u_to int(4) not null,
    message varchar(3000),
    m_type varchar(20) not null,
    quiz_id int(6),
    seen int(1),
	constraint foreign key (u_from) references Users(user_id)
);

CREATE TABLE History (
	id int(5) not null auto_increment primary key,
    user_id int(4) not null,
    quiz_id int (4) not null,
    score int(3) not null,
    start_time time,
    end_time time,
	f_time int(8),
    constraint foreign key (user_id) references Users(user_id),
    constraint foreign key (quiz_id) references Quizes(quiz_id)
);

CREATE TABLE Categories (
	id int(2) not null auto_increment primary key,
    c_name varchar(100) not null
);

CREATE TABLE achievements(
	id int(11) not null auto_increment primary key,
	name varchar(30) not null,
	description varchar(100) not null
);

CREATE TABLE user_achievements(
	user_id int(4) not null,
	achieve_id int(11) not null
);

CREATE TABLE administrators(
	id int(4) not null auto_increment primary key,
	user_id int(4) not null,
	-- High priority means higher access level-- 
	priority int(11) default 0
);

