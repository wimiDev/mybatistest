CREATE table teacher(
	id INT(10) NOT NULL,
    name VARCHAR(30) DEFAULT NULL,
    PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT into teacher(id, name) VALUES(1, '李富贵');

CREATE table student(
	id INT(10) NOT NULL,
    name VARCHAR(30) DEFAULT NULL,
    tid INT(10) DEFAULT NULL,
    PRIMARY KEY(id),
    KEY fktid (tid),
    CONSTRAINT fktid FOREIGN KEY (tid) REFERENCES teacher (id)
)ENGINE=InnoDB DEFAULT CHARSET = utf8;

INSERT INTO  student (id, name, tid) VALUES (1, '大黄', 1);
INSERT INTO  student (id, name, tid) VALUES (2, '小明', 1);
INSERT INTO  student (id, name, tid) VALUES (3, '小红', 1);
INSERT INTO  student (id, name, tid) VALUES (4, '小九', 1);
INSERT INTO  student (id, name, tid) VALUES (5, '小蔡', 1);