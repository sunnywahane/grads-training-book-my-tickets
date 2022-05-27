ALTER TABLE movies
ADD duration Integer;

update movies
set duration =  Extract(EPOCH from (end_time- start_time))/60;

alter table movies drop column start_time;
alter table movies drop column end_time;
