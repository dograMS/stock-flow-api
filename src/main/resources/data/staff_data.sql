
--password = password

do '

declare

--    u_id1 bigint;
--    u_id2 bigint;
--    u_id2 bigint;

begin

insert into Staff (username, password)
        values (''test'', ''$2a$12$BmeFY4a8Tx5ZKh8qjY0bX..sR2TdrrT8HmV2HY.10LqvG6.5TIQqi'');
insert into Staff (username, password)
        values (''admin'', ''$2a$12$BmeFY4a8Tx5ZKh8qjY0bX..sR2TdrrT8HmV2HY.10LqvG6.5TIQqi'');
insert into Staff (username, password)
        values (''staff'', ''$2a$12$BmeFY4a8Tx5ZKh8qjY0bX..sR2TdrrT8HmV2HY.10LqvG6.5TIQqi'');



end; '
language plpgsql;