-- 1
create or replace view Q1(pid, firstname, lastname) as
SELECT
	pid,
	firstname,
	lastname 
FROM
	person 
WHERE
	pid NOT IN ( SELECT pid FROM client ) 
	AND pid NOT IN ( SELECT pid FROM staff ) 
ORDER BY
	pid ASC;

-- 2
create or replace view Q2(pid, firstname, lastname) as
SELECT
	pid,
	firstname,
	lastname 
FROM
	person 
WHERE
	pid NOT IN (
	SELECT DISTINCT
		client.pid 
	FROM
		client,
		insured_by,
		policy 
	WHERE
		client.cid = insured_by.cid 
		AND insured_by.pno = policy.pno 
		AND policy.status = 'E' 
	) 
ORDER BY
	person.pid ASC;

-- 3
create or replace view Q3(brand, vid, pno, premium) as SELECT
	* 
FROM
	(
	SELECT
		tmp3.brand,
		tmp3.ID,
		tmp3.pno,
		tmp2.total2 
	FROM
		(
		SELECT
			coverage.pno,
			SUM ( tmp.total ) total2 
		FROM
			coverage
			JOIN ( SELECT rating_record.coid, SUM ( rating_record.rate ) total FROM rating_record WHERE rating_record.status = 'A' GROUP BY rating_record.coid ) AS tmp ON tmp.coid = coverage.coid 
		GROUP BY
			coverage.pno 
		) tmp2,
		(
		SELECT
			insured_item.brand,
			insured_item.ID,
			policy.pno 
		FROM
			insured_item
			LEFT JOIN policy ON insured_item.ID = policy.ID 
		WHERE
			policy.status = 'E' 
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
	SELECT MAX
		( tmp5.total2 ) 
	FROM
		(
		SELECT
			tmp3.brand,
			tmp3.ID,
			tmp3.pno,
			tmp2.total2 
		FROM
			(
			SELECT
				coverage.pno,
				SUM ( tmp.total ) total2 
			FROM
				coverage
				JOIN ( SELECT rating_record.coid, SUM ( rating_record.rate ) total FROM rating_record WHERE rating_record.status = 'A' GROUP BY rating_record.coid ) AS tmp ON tmp.coid = coverage.coid 
			GROUP BY
				coverage.pno 
			) tmp2,
			(
			SELECT
				insured_item.brand,
				insured_item.ID,
				policy.pno 
			FROM
				insured_item
				LEFT JOIN policy ON insured_item.ID = policy.ID 
			WHERE
				policy.status = 'E' 
			) tmp3 
		WHERE
			tmp2.pno = tmp3.pno 
		ORDER BY
			brand ASC,
			tmp3.ID ASC,
			tmp3.pno ASC 
		) tmp5 
	WHERE
	tmp5.brand = tmp4.brand 
	);

-- 4
create or replace view Q4(pid, firstname, lastname) as
SELECT
	person.pid,
	person.firstname,
	person.lastname 
FROM
	person
	JOIN staff ON person.pid = staff.pid 
WHERE
	staff.sid NOT IN (
	SELECT
		policy.sid 
	FROM
		policy 
	WHERE
		policy.status = 'E' UNION
	SELECT
		rated_by.sid 
	FROM
		rated_by
		JOIN rating_record ON rated_by.rid = rating_record.rid
		JOIN coverage ON coverage.coid = rating_record.coid 
	WHERE
		coverage.pno IN ( SELECT policy.pno FROM policy WHERE policy.status = 'E' ) UNION
	SELECT
		underwritten_by.sid 
	FROM
		underwritten_by
		JOIN underwriting_record ON underwritten_by.urid = underwriting_record.urid 
	WHERE
		underwriting_record.pno IN ( SELECT policy.pno FROM policy WHERE policy.status = 'E' ) 
	) 
ORDER BY
	person.pid ASC;

-- 5
create or replace view Q5(suburb, npolicies) as
SELECT UPPER
	( suburb ),
	"count" ( * ) cnt 
FROM
	(
	SELECT
		person.*,
		insured_by.* 
	FROM
		person,
		client,
		insured_by,
		policy
	WHERE
		person.pid = client.pid 
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
SELECT
	policy.pno,
	policy.ptype,
	person.pid,
	person.firstname,
	person.lastname 
FROM
	policy
	JOIN staff ON policy.sid = staff.sid
	JOIN person ON person.pid = staff.pid 
WHERE
	policy.pno IN (
	SELECT
		tmp.pno 
	FROM
		(-- policy
		SELECT
			policy.pno,
			staff.sid 
		FROM
			policy
			JOIN staff ON policy.sid = staff.sid
			JOIN person ON person.pid = staff.pid 
		WHERE
			policy.status = 'E' UNION-- coverage
		SELECT
			coverage.pno,
			rated_by.sid 
		FROM
			coverage
			JOIN rating_record ON coverage.coid = rating_record.coid
			JOIN rated_by ON rated_by.rid = rating_record.rid 
		WHERE
			coverage.pno IN ( SELECT policy.pno FROM policy WHERE policy.status = 'E' ) UNION-- underwriting_record
		SELECT
			underwriting_record.pno,
			underwritten_by.sid 
		FROM
			underwriting_record
			JOIN underwritten_by ON underwritten_by.urid = underwriting_record.urid 
		WHERE
			underwriting_record.pno IN ( SELECT policy.pno FROM policy WHERE policy.status = 'E' ) 
		) AS tmp 
	WHERE
		1 = 1 
	GROUP BY
		tmp.pno 
	HAVING
		COUNT ( * ) = 1 
	ORDER BY
	tmp.pno 
);

-- 7
-- 6 | G | 2018-12-16 | 2020-12-16 | 32500

-- 8
create or replace view Q8(pid, name, brand) as
SELECT DISTINCT
	person.pid,
	concat_ws ( ' ', person.firstname, person.lastname ),
	insured_item.brand 
FROM
	policy
	JOIN insured_item ON policy.ID = insured_item.
	ID JOIN staff ON policy.sid = staff.sid
	JOIN person ON staff.pid = person.pid
	JOIN (
	SELECT
		tmp.sid 
	FROM
		(
		SELECT DISTINCT
			policy.sid,
			insured_item.brand 
		FROM
			policy
			JOIN insured_item ON policy.ID = insured_item.
			ID JOIN staff ON policy.sid = staff.sid 
		WHERE
			policy.status = 'E' 
		) tmp 
	GROUP BY
		tmp.sid 
	HAVING
		COUNT ( * ) = 1 
	) tmp2 ON tmp2.sid = policy.sid 
WHERE
	policy.status = 'E' 
ORDER BY
	pid ASC;


-- 9
create or replace view Q9(pid, name) as
SELECT
	person.pid,
	concat_ws ( ' ', person.firstname, person.lastname ) 
FROM
	person
	JOIN client ON client.pid = person.pid
	JOIN (
	SELECT
		cid 
	FROM
		(
		SELECT DISTINCT
			insured_by.cid,
			insured_item.brand 
		FROM
			insured_by
			JOIN policy ON policy.pno = insured_by.pno
			JOIN insured_item ON policy.ID = insured_item.ID 
		) AS tmp2 
	GROUP BY
		cid 
	HAVING
	COUNT ( * ) = ( SELECT COUNT ( * ) brand_num FROM ( SELECT brand, COUNT ( * ) FROM insured_item GROUP BY brand ) tmp )) tmp3 ON tmp3.cid = client.cid 
ORDER BY
	person.pid;

-- 10
create or replace function staffcount(pno integer) returns integer
AS 
	$BODY$  
	DECLARE
	res INTEGER;
BEGIN
	SELECT
		"count" ( * ) cnt INTO res 
	FROM
		(
		SELECT
			policy.sid 
		FROM
			policy 
		WHERE
			policy.pno = $1 UNION
		SELECT
			rated_by.sid 
		FROM
			rated_by
			JOIN rating_record ON rated_by.rid = rating_record.rid
			JOIN coverage ON coverage.coid = rating_record.coid 
		WHERE
			coverage.pno = $1 UNION
		SELECT
			underwritten_by.sid 
		FROM
			underwritten_by
			JOIN underwriting_record ON underwritten_by.urid = underwriting_record.urid 
		WHERE
			underwriting_record.pno = $1 
		) tmp;
	RETURN res;

END;
$BODY$ 
LANGUAGE plpgsql VOLATILE;

-- 11

-- 12
