INSERT INTO users (username, password, enabled)
 values
 ('bhure', 'bhure', true);

 INSERT INTO users (username, password, enabled)
  values
 ('user', 'user', true);

 INSERT INTO authorities (username, authority)
 VALUES('user', 'ROLE_USER');

  INSERT INTO authorities (username, authority)
  VALUES
 ('bhure', 'ROLE_ADMIN');