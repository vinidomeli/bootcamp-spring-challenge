CREATE TABLE user
(
    id       BINARY(16)  NOT NULL,
    name     VARCHAR(50) NOT NULL,
    username VARCHAR(40) NOT NULL,
    type     VARCHAR(15),

    PRIMARY KEY (id)
);

CREATE TABLE product_category
(
    id          INT AUTO_INCREMENT,
    description VARCHAR(50),

    PRIMARY KEY (id)
);

CREATE TABLE product
(
    id          INT         NOT NULL AUTO_INCREMENT,
    user_id     VARCHAR(33) NOT NULL,
    alias       VARCHAR(20) NOT NULL,
    name        VARCHAR(30) NOT NULL,
    type        VARCHAR(20) NOT NULL,
    brand       VARCHAR(20) NOT NULL,
    color       VARCHAR(20) NOT NULL,
    category_id INT         NOT NULL,
    notes       VARCHAR(150),

    PRIMARY KEY (id),
    FOREIGN KEY (user_id) references USER (id),
    FOREIGN KEY (category_id) references PRODUCT_CATEGORY (id)
);

CREATE TABLE activity_follows
(
    id          BINARY(16) NOT NULL,
    source_user BINARY(16) NOT NULL,
    target_user BINARY(16) NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (source_user) REFERENCES USER (id),
    FOREIGN KEY (target_user) REFERENCES USER (id)
);

CREATE TABLE activity_post
(
    id         BINARY(16) NOT NULL,
    user_id    BINARY(16) NOT NULL,
    product_id INT        NOT NULL,
    price      DOUBLE     NOT NULL,
    created_at DATETIME   NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES USER (id),
    FOREIGN KEY (product_id) REFERENCES PRODUCT (id)
);

CREATE TABLE activity_promo
(
    id         BINARY(16) NOT NULL,
    post_id    BINARY(16) NOT NULL,
    discount   DOUBLE     NOT NULL,
    created_at DATETIME   NOT NULL,
    active     BOOLEAN    NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (post_id) REFERENCES ACTIVITY_POST (id)
);