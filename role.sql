--create role admin
drop role if exists admin;
create role admin login password 'admin';
--revoke all on all tables in schema public from admin;

--create role mod
drop role if exists mod;
create role mod login password 'mod';
--revoke all on all tables in schema public from mod;

--create role guest
drop role if exists guest;
create role guest login password 'guest';
--revoke all on all tables in schema public from guest;

--grant role admin
grant all on account to admin;
grant all on aircraftinfo to admin;
grant all on airline to admin;
grant all on airport to admin;
grant all on realflight to admin;
grant all on route to admin;
grant all on schedule to admin;

--grant role mod
grant update (password), select on account to mod;
grant all on realflight to mod;
grant select on route to mod;
grant select on airline to mod;
grant select on schedule to mod;
grant select on aircraftinfo to mod;

--grant role guest
grant select on airline to guest;
grant select on airport to guest;
grant select on route to guest;
grant select on schedule to guest;
grant select on realflight to guest;