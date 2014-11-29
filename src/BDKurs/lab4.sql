#1
SELECT
  bachelor.code,
  bachelor.fullName,
  specialty.fullName
FROM specialty
  INNER JOIN bachelor
    ON bachelor.code = specialty.codeBachelor;
#2
SELECT
  specialty.fullName,
  COUNT(ALL student.codeSpecialty)
FROM specialty, student
WHERE specialty.code = student.codeSpecialty
GROUP BY specialty.fullName;

SELECT
  specialty.fullName,
  COUNT(ALL student.codeSpecialty)
FROM specialty
  INNER JOIN student
    ON specialty.code = student.codeSpecialty
GROUP BY specialty.fullName;
#3
SELECT
  student.course
FROM student
GROUP BY course
HAVING COUNT(DISTINCT student.idstudent) = 1;
#4
SELECT
  student.group,
  student.codeSpecialty
FROM student
WHERE student.codeSpecialty IN (SELECT
                                  student.codeSpecialty
                                FROM student
                                WHERE student.idstudent = 2);

#5
SELECT
  bachelor.fullName,
  student.name
FROM bachelor
  LEFT JOIN student
    ON bachelor.code = student.codeBachelor
WHERE student.name IS null;
