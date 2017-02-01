drop table if exists Country;
create table Country (
	country_id				int				identity,
	name					nvarchar(100)	not null,
	iso_name2				varchar(2)		not null /*unique*/,
	capital_city			nvarchar(100)	not null,
	constraint PK_COUNTRY primary key (country_id)
);