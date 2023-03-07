CREATE TABLE stores (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255),
  latitude DOUBLE NOT NULL,
  longitude DOUBLE NOT NULL
);

CREATE TABLE courier (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255)
);

CREATE TABLE travel (
  id INT AUTO_INCREMENT PRIMARY KEY,
  courierId INT,
  latitude DOUBLE,
  longitude DOUBLE,
  FOREIGN KEY (courierId) REFERENCES courier(id)
);

CREATE TABLE entrances (
  id INT AUTO_INCREMENT PRIMARY KEY,
  storeId INT,
  courierId INT,
  entrance_date DATE,
  FOREIGN KEY (storeId) REFERENCES stores(id),
  FOREIGN KEY (courierId) REFERENCES courier(id)
);

INSERT INTO stores (name, latitude, longitude)
VALUES
  ('Ataşehir MMM Migros', 40.9923307, 29.1244229),
  ('Novada MMM Migros', 40.986106, 29.1161293),
  ('Beylikdüzü 5M Migros', 41.0066851, 28.6552262),
  ('Ortaköy MMM Migros', 41.055783, 29.0210292),
  ('Caddebostan MMM Migros', 40.9632463, 29.0630908);
