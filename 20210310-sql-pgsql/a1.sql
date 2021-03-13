-- 1
create or replace view Q1(pid, firstname, lastname) as
SELECT pid, firstname, lastname 
FROM person 
WHERE 
	pid NOT IN (SELECT pid FROM client) 
	AND pid NOT IN (SELECT pid FROM staff) 
ORDER BY
	pid ASC;

-- 2
create or replace view Q2(pid, firstname, lastname) as
SELECT pid, firstname, lastname 
FROM person 
WHERE
	pid NOT IN (
		SELECT DISTINCT client.pid 
		FROM client, insured_by, policy 
		WHERE
			client.cid = insured_by.cid 
			AND insured_by.pno = policy.pno 
			AND policy.status = 'E' 
	) 
ORDER BY person.pid ASC;

-- 3
create or replace view Q3(brand, vid, pno, premium) as 
SELECT *
FROM (
	SELECT tmp3.brand, tmp3.ID, tmp3.pno, tmp2.total2 
	FROM (
			SELECT coverage.pno, SUM ( tmp.total ) total2 
			FROM coverage
				JOIN (
					SELECT rating_record.coid, SUM ( rating_record.rate ) total 
					FROM rating_record 
					WHERE rating_record.status = 'A' 
					GROUP BY rating_record.coid
				) AS tmp ON tmp.coid = coverage.coid 
			GROUP BY coverage.pno 
		) tmp2, (
			SELECT insured_item.brand, insured_item.ID, policy.pno 
			FROM insured_item LEFT JOIN policy ON insured_item.ID = policy.ID 
			WHERE policy.status = 'E' 
		) tmp3 
	WHERE
		tmp2.pno = tmp3.pno 
	ORDER BY
		brand ASC,
		tmp3.ID ASC,
		tmp3.pno ASC 
	) tmp4 
WHERE
	tmp4.total2 = (
		SELECT MAX(tmp5.total2) 
		FROM (
			SELECT tmp3.brand, tmp3.ID, tmp3.pno, tmp2.total2 
			FROM (
				SELECT coverage.pno, SUM(tmp.total) total2 
				FROM coverage
					JOIN ( 
						SELECT rating_record.coid, SUM(rating_record.rate) total 
						FROM rating_record 
						WHERE rating_record.status = 'A' 
						GROUP BY rating_record.coid 
					) AS tmp ON tmp.coid = coverage.coid 
				GROUP BY
					coverage.pno 
				) tmp2,
				(
				SELECT insured_item.brand, insured_item.ID, policy.pno 
				FROM insured_item LEFT JOIN policy ON insured_item.ID = policy.ID 
				WHERE policy.status = 'E' 
				) tmp3 
			WHERE
				tmp2.pno = tmp3.pno 
			ORDER BY brand ASC, tmp3.ID ASC, tmp3.pno ASC 
		) tmp5 
		WHERE
		tmp5.brand = tmp4.brand 
	);

-- 4
create or replace view Q4(pid, firstname, lastname) as
SELECT person.pid, person.firstname, person.lastname 
FROM person JOIN staff ON person.pid = staff.pid 
WHERE 
	staff.sid NOT IN (
		SELECT policy.sid 
		FROM policy 
		WHERE policy.status = 'E' 
		UNION
		SELECT rated_by.sid 
		FROM rated_by
			JOIN rating_record ON rated_by.rid = rating_record.rid
			JOIN coverage ON coverage.coid = rating_record.coid 
		WHERE coverage.pno IN (SELECT policy.pno FROM policy WHERE policy.status = 'E') 
		UNION
		SELECT underwritten_by.sid 
		FROM underwritten_by
			JOIN underwriting_record ON underwritten_by.urid = underwriting_record.urid 
		WHERE underwriting_record.pno IN (SELECT policy.pno FROM policy WHERE policy.status = 'E') 
	) 
ORDER BY
	person.pid ASC;

-- 5
create or replace view Q5(suburb, npolicies) as
SELECT UPPER(suburb), count(*) cnt 
FROM (
	SELECT person.*, insured_by.* 
	FROM person, client, insured_by, policy
	WHERE person.pid = client.pid 
		AND client.cid = insured_by.cid
		AND policy.pno = insured_by.pno
		AND person.STATE = 'NSW' 
		AND policy.status = 'E'
	) AS tmp 
WHERE
	1 = 1 
GROUP BY
	suburb 
ORDER BY
	cnt ASC,
	suburb ASC;

-- 6
create or replace view Q6(pno, ptype, pid, firstname, lastname) as 
SELECT policy.pno, policy.ptype, person.pid, person.firstname, person.lastname 
FROM policy
	JOIN staff ON policy.sid = staff.sid
	JOIN person ON person.pid = staff.pid 
WHERE
	policy.pno IN (
	SELECT tmp.pno 
	FROM (
		SELECT policy.pno, staff.sid 
		FROM policy
			JOIN staff ON policy.sid = staff.sid
			JOIN person ON person.pid = staff.pid 
		WHERE policy.status = 'E' 
		UNION
		SELECT coverage.pno, rated_by.sid 
		FROM coverage
			JOIN rating_record ON coverage.coid = rating_record.coid
			JOIN rated_by ON rated_by.rid = rating_record.rid 
		WHERE coverage.pno IN (SELECT policy.pno FROM policy WHERE policy.status = 'E') 
		UNION
		SELECT underwriting_record.pno, underwritten_by.sid 
		FROM underwriting_record
			JOIN underwritten_by ON underwritten_by.urid = underwriting_record.urid 
		WHERE underwriting_record.pno IN (SELECT policy.pno FROM policy WHERE policy.status = 'E') 
	) AS tmp 
	WHERE
		1 = 1 
	GROUP BY
		tmp.pno 
	HAVING
		COUNT (*) = 1 
	ORDER BY tmp.pno ASC 
);

-- 7
create or replace view Q7(pno, ptype, effectivedate, expirydate, agreedvalue) as
SELECT policy.pno, policy.ptype, policy.effectivedate, policy.expirydate, policy.agreedvalue 
FROM (
	SELECT tmp1.pno, tmp1.first_rdate, tmp2.last_wdate 
	FROM (
		SELECT coverage.pno, MIN(rated_by.rdate) first_rdate 
		FROM coverage
			JOIN rating_record ON rating_record.coid = coverage.coid
			JOIN rated_by ON rated_by.rid = rating_record.rid 
		GROUP BY
			coverage.pno 
	) tmp1
	JOIN (
		SELECT underwriting_record.pno, MAX(underwritten_by.wdate) last_wdate 
		FROM underwritten_by
			JOIN underwriting_record ON underwritten_by.urid = underwriting_record.urid 
		GROUP BY
			underwriting_record.pno 
	) tmp2 ON tmp1.pno = tmp2.pno 
) tmp3 JOIN policy ON tmp3.pno = policy.pno 
WHERE (tmp3.last_wdate - tmp3.first_rdate ) = (
	SELECT MAX(tmp6.last_wdate - tmp6.first_rdate ) 
	FROM (
		SELECT tmp4.pno, tmp4.first_rdate, tmp5.last_wdate 
		FROM (
			SELECT coverage.pno, MIN(rated_by.rdate) first_rdate 
			FROM coverage
				JOIN rating_record ON rating_record.coid = coverage.coid
				JOIN rated_by ON rated_by.rid = rating_record.rid 
			GROUP BY
				coverage.pno 
		) tmp4
		JOIN (
			SELECT underwriting_record.pno, MAX(underwritten_by.wdate) last_wdate 
			FROM underwritten_by
				JOIN underwriting_record ON underwritten_by.urid = underwriting_record.urid 
			GROUP BY
				underwriting_record.pno 
		) tmp5 ON tmp4.pno = tmp5.pno
		JOIN policy ON tmp4.pno = policy.pno 
		WHERE
			policy.status = 'E' 
		) tmp6 
);

-- 8
create or replace view Q8(pid, name, brand) as
SELECT DISTINCT person.pid, concat_ws ( ' ', person.firstname, person.lastname ), insured_item.brand 
FROM policy
	JOIN insured_item ON policy.ID = insured_item.ID 
	JOIN staff ON policy.sid = staff.sid
	JOIN person ON staff.pid = person.pid
	JOIN (
		SELECT tmp.sid 
		FROM (
			SELECT DISTINCT policy.sid, insured_item.brand 
			FROM policy
				JOIN insured_item ON policy.ID = insured_item.ID 
				JOIN staff ON policy.sid = staff.sid 
			WHERE
				policy.status = 'E' 
		) tmp 
		GROUP BY tmp.sid 
		HAVING COUNT(*) = 1 
	) tmp2 ON tmp2.sid = policy.sid 
WHERE policy.status = 'E' 
ORDER BY pid ASC;


-- 9
create or replace view Q9(pid, name) as
SELECT person.pid, concat_ws ( ' ', person.firstname, person.lastname ) 
FROM person
	JOIN client ON client.pid = person.pid
	JOIN (
	SELECT cid 
	FROM (
		SELECT DISTINCT insured_by.cid, insured_item.brand 
		FROM insured_by
			JOIN policy ON policy.pno = insured_by.pno
			JOIN insured_item ON policy.ID = insured_item.ID 
	) AS tmp2 
	GROUP BY cid 
	HAVING COUNT(*) = (SELECT COUNT(*) brand_num FROM (SELECT brand, COUNT(*) FROM insured_item GROUP BY brand) tmp )) tmp3 ON tmp3.cid = client.cid 
ORDER BY person.pid;

-- 10
create or replace function staffcount(pno integer) returns integer AS 
$BODY$  
DECLARE
	res INTEGER;
BEGIN
	SELECT count(*) cnt INTO res 
	FROM (
		SELECT policy.sid FROM policy WHERE policy.pno = $1 
		UNION
		SELECT rated_by.sid 
		FROM
			rated_by
			JOIN rating_record ON rated_by.rid = rating_record.rid
			JOIN coverage ON coverage.coid = rating_record.coid 
		WHERE coverage.pno = $1 
		UNION
		SELECT underwritten_by.sid 
		FROM underwritten_by
			JOIN underwriting_record ON underwritten_by.urid = underwriting_record.urid 
		WHERE underwriting_record.pno = $1 
	) tmp;
	RETURN res;

END;
$BODY$ 
LANGUAGE plpgsql VOLATILE;

-- 11
create or replace procedure renew(pno integer) RETURNS INTEGER AS 
$BODY$ 
DECLARE
	is_effective bool;
	has_same_vehicle bool;
	sql_query_policy VARCHAR;
	old_policy RECORD;
	new_id_policy INTEGER;
	sql_query_coverage VARCHAR;
	row_coverage RECORD;
	new_id_coverage INTEGER;
BEGIN
	sql_query_policy := E'SELECT * FROM policy WHERE pno = \'' || pno || E'\'';
	EXECUTE sql_query_policy INTO old_policy;
	IF old_policy = NULL THEN
		RETURN 1;
	END IF;
	
	is_effective := (SELECT (CURRENT_DATE <= policy.expirydate AND policy.status = 'E') FROM policy WHERE policy.pno = $1);
	IF is_effective = TRUE THEN
		has_same_vehicle := (SELECT count(*)>0 FROM policy WHERE policy.ptype = old_policy.ptype AND policy.pno <> old_policy.pno AND policy.id = old_policy.id);
		IF has_same_vehicle = TRUE THEN
			RETURN 2;
		END IF;
		-- new policy
		new_id_policy := (SELECT (MAX(policy.pno) + 1) AS new_id FROM policy);
		INSERT INTO policy(pno, ptype, status, effectivedate, expirydate, agreedvalue, comments, sid, ID) VALUES (new_id_policy, old_policy.ptype, 'D', CURRENT_DATE, CURRENT_DATE + (old_policy.expirydate - old_policy.effectivedate), old_policy.agreedvalue, old_policy.comments, old_policy.sid, old_policy.ID );
		-- old policy
		UPDATE policy SET expirydate = CURRENT_DATE WHERE policy.pno = $1;
		-- new coverage
		sql_query_coverage := E'SELECT * FROM coverage WHERE coverage.pno = \'' || pno || E'\'';
		FOR row_coverage IN EXECUTE sql_query_coverage LOOP
			new_id_coverage := ( SELECT (MAX(coverage.coid) + 1) AS new_id FROM coverage);
			INSERT INTO coverage(coid, cname, maxamount, comments, pno) VALUES(new_id_coverage, row_coverage.cname, row_coverage.maxamount, row_coverage.comments, new_id_policy);		
		END LOOP;
	ELSE 
		new_id_policy := (SELECT (MAX( policy.pno) + 1) AS new_id FROM policy );
		INSERT INTO policy(pno, ptype, status, effectivedate, expirydate, agreedvalue, comments, sid, ID) VALUES (new_id_policy, old_policy.ptype, 'D', CURRENT_DATE, CURRENT_DATE + ( old_policy.expirydate - old_policy.effectivedate), old_policy.agreedvalue, old_policy.comments, old_policy.sid, old_policy.ID);
		-- new coverage
		sql_query_coverage := E'SELECT * FROM coverage WHERE coverage.pno = \'' || pno || E'\'';
		FOR row_coverage IN EXECUTE sql_query_coverage LOOP
			new_id_coverage := ( SELECT (MAX(coverage.coid) + 1) AS new_id FROM coverage);
			INSERT INTO coverage(coid, cname, maxamount, comments, pno) VALUES (new_id_coverage, row_coverage.cname, row_coverage.maxamount, row_coverage.comments, new_id_policy);
		END LOOP;
	END IF;
	RETURN 0;
END;
$BODY$ 
LANGUAGE plpgsql VOLATILE;

-- 12
DROP TRIGGER IF EXISTS tri_after_insert_insured_by ON insured_by;
CREATE OR REPLACE FUNCTION func_tri_after_insert_insured_by () RETURNS TRIGGER AS 
$BODY$ 
DECLARE
	new_sid INTEGER;
	involved_sid INTEGER;
BEGIN
	new_sid := (SELECT staff.sid FROM public.client JOIN public.person ON client.pid = person.pid JOIN public.staff ON staff.pid = person.pid WHERE client.cid = NEW.cid);
	IF new_sid = NULL THEN
		RETURN NEW;
	END IF;

	involved_sid := (SELECT tmp.sid FROM (
			SELECT policy.sid FROM public.policy WHERE policy.pno = NEW.pno 
			UNION
			SELECT rated_by.sid FROM public.rated_by JOIN public.rating_record ON rated_by.rid = rating_record.rid JOIN public.coverage ON coverage.coid = rating_record.coid WHERE coverage.pno = NEW.pno 
			UNION
			SELECT underwritten_by.sid FROM public.underwritten_by JOIN public.underwriting_record ON underwriting_record.urid = underwritten_by.urid WHERE underwriting_record.pno = NEW.pno 
		) tmp 
		WHERE tmp.sid = new_sid);
	IF involved_sid = NULL THEN
		RETURN NEW;
	END IF;
	DELETE FROM public.insured_by WHERE insured_by.cid = NEW.cid AND insured_by.pno = NEW.pno;
	RETURN NEW;
END;
$BODY$ 
LANGUAGE plpgsql VOLATILE;

CREATE TRIGGER tri_after_insert_insured_by AFTER INSERT ON insured_by FOR EACH ROW
EXECUTE PROCEDURE func_tri_after_insert_insured_by ();