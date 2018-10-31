CREATE TABLE user
(
  id BIGINT NOT NULL AUTO_INCREMENT,
  version INT NOT NULL,
  created TIMESTAMP NOT NULL,
  updated TIMESTAMP NULL,
  username VARCHAR(32) NOT NULL UNIQUE,
  password VARCHAR(64) NOT NULL,
  first_name VARCHAR(32) NOT NULL,
  last_name VARCHAR(32) NOT NULL,
  birth_date DATE NOT NULL,
  gender VARCHAR(8) NOT NULL,
  email VARCHAR(64) NOT NULL UNIQUE,
  phone VARCHAR(16),
  PRIMARY KEY (ID)
);

CREATE TABLE user_role
(
  user_id BIGINT NOT NULL REFERENCES user(id),
  role VARCHAR(16) NOT NULL
);

-- Pass: 123456
INSERT INTO user VALUES(1, 0, '2014-12-01 12:00', NULL, 'admin',
  '$2a$10$cYLM.qoXpeAzcZhJ3oXRLu9Slkb61LHyWW5qJ4QKvHEMhaxZ5qCPi',
  'Lutfun', 'Nahar', '2014-12-01', 'FEMALE', 'rubycse@yahoo.com', '01789785766');
