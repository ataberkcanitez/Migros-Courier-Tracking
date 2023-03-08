CREATE TABLE stores (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255),
  latitude DOUBLE NOT NULL,
  longitude DOUBLE NOT NULL
) DEFAULT CHARSET=utf8;

CREATE TABLE courier (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255)
) DEFAULT CHARSET=utf8;

CREATE TABLE travel (
  id INT AUTO_INCREMENT PRIMARY KEY,
  courier_id INT,
  latitude DOUBLE,
  longitude DOUBLE,
  travel_date timestamp,
  FOREIGN KEY (courier_id) REFERENCES courier(id)
) DEFAULT CHARSET=utf8;

CREATE TABLE entrances (
  id INT AUTO_INCREMENT PRIMARY KEY,
  store_id INT,
  courier_id INT,
  entrance_date timestamp,
  FOREIGN KEY (store_id) REFERENCES stores(id),
  FOREIGN KEY (courier_id) REFERENCES courier(id)
) DEFAULT CHARSET=utf8;

INSERT INTO stores (name, latitude, longitude)
VALUES
  ('Ataşehir MMM Migros', 40.9923307, 29.1244229),
  ('Novada MMM Migros', 40.986106, 29.1161293),
  ('Beylikdüzü 5M Migros', 41.0066851, 28.6552262),
  ('Ortaköy MMM Migros', 41.055783, 29.0210292),
  ('Caddebostan MMM Migros', 40.9632463, 29.0630908);

INSERT INTO courier (name) VALUES ('courier1');
INSERT INTO courier (name) VALUES ('courier2');
INSERT INTO courier (name) VALUES ('courier3');
INSERT INTO courier (name) VALUES ('courier4');