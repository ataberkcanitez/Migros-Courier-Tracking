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
