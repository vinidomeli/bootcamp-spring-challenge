CREATE TABLE USER(
    id VARCHAR(20) NOT NULL,
    name VARCHAR(50) NOT NULL,
    username VARCHAR(40) NOT NULL,
    type VARCHAR(15),

    PRIMARY KEY(id)
);

CREATE TABLE PRODUCT_CATEGORY(
    id INT AUTO_INCREMENT,
    description VARCHAR(50),

    PRIMARY KEY (id)
);

CREATE TABLE PRODUCT(
    id INT NOT NULL AUTO_INCREMENT,
    user_id VARCHAR(20) NOT NULL,
    alias VARCHAR(20) NOT NULL,
    name VARCHAR(30) NOT NULL,
    type VARCHAR(20) NOT NULL,
    brand VARCHAR(20) NOT NULL,
    color VARCHAR(20) NOT NULL,
    category_id INT NOT NULL,
    notes VARCHAR(150),

    PRIMARY KEY (id),
    FOREIGN KEY (user_id) references USER(id),
    FOREIGN KEY (category_id) references PRODUCT_CATEGORY(id)
);

CREATE TABLE ACTIVITY_FOLLOWS(
    id VARCHAR(20) NOT NULL,
    source_user VARCHAR(20) NOT NULL,
    target_user VARCHAR(20) NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (source_user) REFERENCES USER(id),
    FOREIGN KEY (target_user) REFERENCES USER(id)
);

CREATE TABLE ACTIVITY_POST(
     id VARCHAR(20) NOT NULL,
     user_id VARCHAR(20) NOT NULL,
     product_id INT NOT NULL,
     price DOUBLE NOT NULL,
     created_at DATETIME NOT NULL,

     PRIMARY KEY (id),
     FOREIGN KEY (user_id) REFERENCES USER(id),
     FOREIGN KEY (product_id) REFERENCES PRODUCT(id)
);

CREATE TABLE ACTIVITY_PROMO(
    id VARCHAR(20) NOT NULL,
    post_id VARCHAR(20) NOT NULL,
    discount DOUBLE NOT NULL,
    created_at DATETIME NOT NULL,
    active BOOLEAN NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (post_id) REFERENCES ACTIVITY_POST(id)
);