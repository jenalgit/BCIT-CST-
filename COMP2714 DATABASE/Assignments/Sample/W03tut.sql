-- q1
select h.hotelName, count(distinct r.type) ntypes, count(distinct r.roomNo) nrooms
from Hotel h join Room r on h.hotelNo = r.hotelNo
where h.hotelAddress like '%London%'
group by h.hotelName
;
-- q2
select h.hotelName, AVG(r.price)
from Hotel h join Room r on h.hotelNo = r.hotelNo
where hotelAddress like '%london%'
group by h.hotelName
having AVG(r.price) < (
						select avg(r.price)
						from Hotel h join Room r on h.hotelNo = r.hotelNo
					  )
;
-- q3
select h.hotelName, r.roomNo, r.price
from Hotel h join Room r on h.hotelNo = r.hotelNo
where h.hotelName like '%Grosvenor%'
  and r.price > (
				select avg(r.price)
				from Hotel h join Room r on h.hotelNo = r.hotelNo
				where h.hotelName like '%Grosvenor%'
				)
;
-- q4
select hr.hotelName, hr.roomNo, bg.guestName
from (
		select h.hotelNo, h.hotelName, r.roomNo
		from Hotel h join Room r on h.hotelNo = r.hotelNo
		where h.hotelName like '%Grosvenor%'
	 ) hr
left join (
			select b.hotelNo, b.roomNo, g.guestName
			from Booking b join Guest g on b.guestNo = g.guestNo
			where b.datefrom < date'2016-11-01'
			  and (b.dateTo is null or b.dateTo >= date'2016-10-01')
		  ) bg
	on hr.hotelNo = bg.hotelNo
	and hr.roomNo = bg.roomNo
order by hr.hotelName,
		 hr.roomNo
;
--
