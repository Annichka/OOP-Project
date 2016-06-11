Drop schema WebData;
CREATE SCHEMA WebData;
USE WebData;

CREATE TABLE Quizes (
	quiz_id int(4) not null auto_increment primary key,
    quiz_name varchar(300) not null,
    author_id int(4) not null, 
    category varchar(100) not null,
    isRandom int(1) not null,
    pages int(1) not null,
    correction int(1) not null,
    practice int(1) not null
);

CREATE TABLE Questions(
	id int(5) not null auto_increment primary key,
    question varchar(200) not null,
    quest_type int(3) not null,
    c_answer text not null,
    w_answer text,
    answer_count int(2) not null,
    pic_url varchar(3000),
    quiz_id int(4) not null,
    constraint foreign key (quiz_id) references Quizes(quiz_id)
);

/* Extension: რამდენი ქვიზი აქვს შექმნილი/გაკეთებული, Achivements და Practice Mode */
CREATE TABLE Users (
	user_id int(4) not null auto_increment primary key,
    username varchar(20) not null,
    pass varchar(100) not null,
    e_mail varchar(50) not null,
    pic_url varchar(3000)
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
	constraint foreign key (u_from) references Users(user_id)
);

CREATE TABLE History (
	id int(5) not null auto_increment primary key,
    user_id int(4) not null,
    quiz_id int (4) not null,
    score int(3) not null,
    constraint foreign key (user_id) references Users(user_id),
    constraint foreign key (quiz_id) references Quizes(quiz_id)
);