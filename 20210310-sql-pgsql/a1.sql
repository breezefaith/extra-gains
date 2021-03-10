-- 1
create or replace view Q1(pid, firstname, lastname) as SELECT pid, firstname, lastname FROM person WHERE pid NOT IN (SELECT pid FROM client) AND pid NOT IN (SELECT pid FROM staff) ORDER BY pid ASC;

-- 2
create or replace view Q2(pid, firstname, lastname) as SELECT pid, firstname, lastname FROM person WHERE pid NOT IN (SELECT DISTINCT client.pid FROM client, insured_by, policy WHERE client.cid = insured_by.cid AND insured_by.pno = policy.pno AND policy.status = 'E') ORDER BY person.pid ASC;

-- 3

-- 4
create or replace view Q4(pid, firstname, lastname) as SELECT person.pid, person.firstname, person.lastname FROM person JOIN staff ON person.pid = staff.pid WHERE staff.sid NOT IN (SELECT policy.sid FROM policy) AND staff.sid NOT IN (SELECT underwritten_by.sid FROM underwritten_by) ORDER BY person.pid ASC;

-- 5
create or replace view Q5(suburb, npolicies) as SELECT UPPER(suburb), "count"(*) cnt FROM (SELECT person.*, insured_by.* FROM person, client, insured_by WHERE person.pid = client.pid AND client.cid = insured_by.cid AND person.state = 'NSW') as tmp WHERE 1=1 GROUP BY suburb ORDER BY cnt DESC, suburb ASC;

-- 6

-- 7

-- 8

-- 9

-- 10

-- 11

-- 12
