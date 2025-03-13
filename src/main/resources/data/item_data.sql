do '
declare
    p_id1 bigint;
    p_id2 bigint;
    p_id3 bigint;

    i_id1 bigint;
    i_id2 bigint;
    i_id3 bigint;
    i_id4 bigint;
    i_id5 bigint;

begin

insert into Party (name, phone, company, email, address)
        values (''Love'', ''6779012345'', ''nike'', ''love@gmail.com'', ''Talwara'')
            returning id into p_id1;--
insert into Party (name, phone, company, email, address)
        values (''Rana'', ''6789712345'', ''Mega'', ''rana@gmail.com'', ''Naushera'')
            returning id into p_id2;--
insert into Party (name, phone, company, email, address)
        values (''meet'', ''6759812345'', ''Vero'', ''meet@gmail.com'', ''Bhangala'')
            returning id into p_id3;--


insert into Item (name, description, base_unit_price)
        values ( ''Cap'', ''Blue colored Red Cap'', 200)
            returning id into i_id1;--
insert into Item (name, description, base_unit_price)
        values (''Bottle'', ''Blue colored Red Bottle'', 222)
            returning id into i_id2;--
insert into Item (name, description, base_unit_price)
        values (''Levi Shoe'', ''Red black check'', 643)
            returning id into i_id3;--
insert into Item (name, description, base_unit_price)
        values (''Cheron Lember'', ''black car red flag 5000 horse power'', 850)
            returning id into i_id4;--
insert into Item (name, description, base_unit_price)
        values (''Duke'', ''Sports bike red check 50cc engine'', 700)
            returning id into i_id5;--

insert into item_providers (item_id, providers_id) values(i_id1, p_id1);
insert into item_providers (item_id, providers_id) values(i_id1, p_id3);
insert into item_providers (item_id, providers_id) values(i_id2, p_id3);
insert into item_providers (item_id, providers_id) values(i_id3, p_id2);
insert into item_providers (item_id, providers_id) values(i_id3, p_id3);
insert into item_providers (item_id, providers_id) values(i_id4, p_id1);
insert into item_providers (item_id, providers_id) values(i_id5, p_id2);
insert into item_providers (item_id, providers_id) values(i_id5, p_id3);


end;
' language plpgsql;