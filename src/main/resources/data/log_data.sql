do '

declare
    p_id1 bigint;
    p_id2 bigint;

    i_id1 bigint;
    i_id2 bigint;

    s_id1 bigint;
    s_id2 bigint;

begin

        insert into Party (name, phone, company, email, address)
                values (''Sevy'', ''6779012765'', ''nike'', ''Sevy@gmail.com'', ''Talwara'')
                    returning id into p_id1;--
        insert into Party (name, phone, company, email, address)
                values (''Gerry'', ''6789718745'', ''Mega'', ''gerry@gmail.com'', ''Naushera'')
                    returning id into p_id2;--

        insert into Item (name, description, base_unit_price)
                values ( ''Shoe'', ''Blue colored Red shoe'', 200)
                    returning id into i_id1;--
        insert into Item (name, description, base_unit_price)
                values (''SkyBT'', ''Blue colored Red Bottle'', 120)
                    returning id into i_id2;--

        insert into item_providers (item_id, providers_id) values(i_id1, p_id1);
        insert into item_providers (item_id, providers_id) values(i_id1, p_id2);
        insert into item_providers (item_id, providers_id) values(i_id2, p_id1);
        insert into item_providers (item_id, providers_id) values(i_id2, p_id2);

        insert into Staff (username, password)
                values (''staff1'', ''$2a$12$BmeFY4a8Tx5ZKh8qjY0bX..sR2TdrrT8HmV2HY.10LqvG6.5TIQqi'')
                returning id into s_id1;--
        insert into Staff (username, password)
                values (''staff2'', ''$2a$12$BmeFY4a8Tx5ZKh8qjY0bX..sR2TdrrT8HmV2HY.10LqvG6.5TIQqi'')
                returning id into s_id2;--

        insert into stock(item_id, provider_id, current_stock, minimum_stock, last_updated)
                values(i_id1, p_id1, 55, 10, now());
        insert into stock(item_id, provider_id, current_stock, minimum_stock, last_updated)
                values(i_id2, p_id2, 13, 5, now());


        insert into log(item_id, party_id, staff_id, quantity, unit_price, type, log_date)
                values (i_id1, p_id1, s_id1, 50, 200, ''PURCHASE'', now());
        insert into log(item_id, party_id, staff_id, quantity, unit_price, type, log_date)
                values (i_id2, p_id2, s_id1, 23, 100, ''PURCHASE'', now());
        insert into log(item_id, party_id, staff_id, quantity, unit_price, type, log_date)
                values (i_id1, p_id1, s_id2, 5, 100, ''PURCHASE'', now());
        insert into log(item_id, party_id, staff_id, quantity, unit_price, type, log_date)
                values (i_id2, p_id2, s_id1, 10, 200, ''SALE'', now());


end;
' language plpgsql;