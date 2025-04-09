update UserBloodSugar
set inputType = 'BS10_BEFORE_BREAKFAST'
where inputType = 'BS05_FASTING_BREAKFAST';

delete
ubs
from UserBloodSugar as ubs
         left join (select max(id) as id, userId, inputDate, inputType
                    from UserBloodSugar
                    group by userId, inputDate, inputType) as ubs2
                   on ubs.userId = ubs2.userId and ubs.inputDate = ubs2.inputDate and
                      ubs.inputType = ubs2.inputType and
                      ubs.id = ubs2.id
where ubs2.id is null;
