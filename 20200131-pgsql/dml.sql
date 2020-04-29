-- 1
SELECT
	payments_accounts.acct_no 
FROM
	payments_accounts
	LEFT JOIN principal_owner ON payments_accounts.acct_no = principal_owner.acct_no
WHERE
	payments_accounts.acct_no = principal_owner.acct_no 
	AND payments_accounts.status = 'Active' 
	AND principal_owner.business_state = 'Montana' 
	AND payments_accounts.acct_no IN (
		SELECT
			payments_transactions.acct_no 
		FROM
			payments_transactions 
		WHERE
			to_char( payments_transactions.transaction_date_time, 'YYYY' ) = '2018' 
		GROUP BY
			payments_transactions.acct_no 
		HAVING
		SUM ( payments_transactions.payment_amount ) > 1000000 
		);

-- 2
SELECT
	t1.processing_network,
	t1.sales_vol,
	t2.return_vol,
	t3.acct_num 
FROM
	( SELECT payments_transactions.processing_network, SUM ( payments_transactions.payment_amount ) sales_vol FROM payments_transactions WHERE payments_transactions.transaction_type = 'Sale' GROUP BY payments_transactions.processing_network ) AS t1
	LEFT JOIN 
	( SELECT payments_transactions.processing_network, SUM ( payments_transactions.payment_amount ) return_vol FROM payments_transactions WHERE payments_transactions.transaction_type = 'Return' GROUP BY payments_transactions.processing_network ) AS t2 
	ON t1.processing_network = t2.processing_network
	LEFT JOIN 
	( SELECT payments_transactions.processing_network, COUNT ( payments_transactions.acct_no ) acct_num  FROM payments_transactions WHERE payments_transactions.acct_no IN ( SELECT acct_no FROM payments_accounts WHERE payments_accounts.status = 'Closed' AND payments_accounts.close_reason = 'Collusion' ) GROUP BY payments_transactions.processing_network ) AS t3 
	ON t1.processing_network = t3.processing_network;
	
-- 3
SELECT
	payments_accounts.acct_no,
	SUM ( payments_transactions.payment_amount ) sale_vol,
	COUNT ( DISTINCT payments_transactions.processing_network ) paid_way_count 
FROM
	payments_transactions
	LEFT JOIN payments_accounts ON payments_transactions.acct_no = payments_accounts.acct_no 
	LEFT JOIN principal_owner ON payments_transactions.acct_no = principal_owner.acct_no
WHERE
	to_char( payments_accounts.open_date, 'YYYY-mm' ) IN ( '2018-06', '2018-07', '2018-08' ) 
	AND
	principal_owner.business_country <> payments_transactions.card_issuer_country
GROUP BY
	payments_accounts.acct_no 
ORDER BY
	sale_vol DESC;
	
-- 4
SELECT
	payments_transactions.acct_no 
FROM
	payments_transactions
	LEFT JOIN payments_accounts ON payments_accounts.acct_no = payments_transactions.acct_no 
WHERE
	payments_accounts.status = 'Active' 
GROUP BY
	payments_transactions.acct_no 
HAVING
	( SELECT SUM ( p1.payment_amount ) FROM payments_transactions p1 WHERE p1.processing_network = 'Prepaid Card' AND p1.acct_no = payments_transactions.acct_no ) / SUM ( payments_transactions.payment_amount ) * 100 >= 80;