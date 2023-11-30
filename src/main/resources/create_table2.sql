drop table listing_companies
CREATE TABLE IF NOT EXISTS listing_companies (
	code varchar(8) unique, -- mã cổ phiếu
	exchange varchar(5), -- sàn giao dịch
	fullname_vi varchar(255), -- tên tiếng việt đầy đủ
	business_type integer, -- loại doanh nghiệp
	created_by varchar(255),
	created_date timestamp,
	modified_by varchar(255),
	modified_date timestamp,
	primary key(code)
);

CREATE TABLE IF NOT EXISTS overview_companies (
	code varchar(8) unique,
	company_name varchar(255), -- tên công ty
	short_name varchar(50), -- tên viết tắt
	industry_id integer, -- mã ngành công nghiệp
	industry_idv2 varchar(5), -- mã ngành 2
	industry_id_level2 varchar(5),
	industry_id_level4 varchar(5),
	industry varchar(255), -- tên ngành công nghiệp
	industry_en varchar(255),
	established_year varchar(5), -- năm thành lập
	no_employees integer, -- số lượng nhân viên
	no_shareholders integer, -- số lượng cổ đông
	foreign_percent double precision, -- tỉ lệ sở hữu nước ngoài
	website varchar(50), -- địa chỉ website
	stock_rating double precision, -- đánh giá cổ phiếu
	delta_in_week double precision, --
	delta_in_month double precision,
	delta_in_year double precision,
	outstanding_share double precision, -- số lượng cổ phiếu đang lưu hành
	issue_share double precision, -- số lượng cổ phiếu phát hành
	company_profile varchar(255), -- Thông tin về công ty
	company_type varchar(5), -- loại công ty
	history_dev varchar(255), -- lịch sử phát triển,
	company_promise varchar(255), -- cam kết của công ty
	busines_risk varchar(255), -- rủi ro kinh doanh của công ty
	key_kevelopments varchar(255), -- các sự kiện , diễn biến quan trọng trong quá trình phát triển
	business_strategies varchar(255), -- chiến lược kinh doanh của công ty
	created_by varchar(255),
	created_date timestamp,
	modified_by varchar(255),
	modified_date timestamp,
	primary key(code),
	FOREIGN KEY (code) REFERENCES listing_companies (code) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS company_leaders  (
	id serial,
	code varchar(8) unique,
	name varchar(50),
	position varchar(50),
	own_percent double precision,
	created_by varchar(255),
	created_date timestamp,
	modified_by varchar(255),
	modified_date timestamp,
	primary key(id),
	FOREIGN KEY (code) REFERENCES listing_companies (code) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS large_share_holders (
	id serial,
	code varchar(8) unique,
	name varchar(50),
	own_percent double precision,
	created_by varchar(255),
	created_date timestamp,
	modified_by varchar(255),
	modified_date timestamp,
	primary key(id),
	FOREIGN KEY (code) REFERENCES listing_companies (code) ON DELETE CASCADE
);



