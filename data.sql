use assessmenttracker;

insert into modules (moduleName, moduleNumber, startYear) values ( 'WebApps', 'CMT612', 2023);
insert into modules (moduleName, moduleNumber, startYear) values ( 'Agile', 'CMT611', 2023);
insert into modules (moduleName, moduleNumber, startYear) values ( 'SDS1', 'CM6113', 2023);
insert into modules (moduleName, moduleNumber, startYear) values ( 'CompThinking', 'CM6114', 2023);
insert into modules (moduleName, moduleNumber, startYear) values ( 'WebApps', 'CMT612', 2024);


insert into users (firstName, lastName) values ( 'Ian', 'Cooper');
insert into users (firstName, lastName) values ( 'Ban', 'Booper');
insert into users (firstName, lastName) values ( 'Dan', 'Dooper');
insert into users (firstName, lastName) values ( 'Ean', 'Eooper');


insert into moduleLeaders (userID, moduleID) values ( '1', '1');
insert into moduleLeaders (userID, moduleID) values ( '1', '2');
insert into moduleLeaders (userID, moduleID) values ( '1', '4');

insert into moduleModerators (userID, moduleID) values ( '2', '1');
insert into moduleModerators (userID, moduleID) values ( '2', '2');
insert into moduleModerators (userID, moduleID) values ( '3', '4');
insert into moduleModerators (userID, moduleID) values ( '1', '3');

insert into assessmentStatus (moduleID, weighting, assessmentName, assessmentReady,readyDate,intModerated)
    values (1, 40, 'initial assessment', true, '2023-12-25', false);
insert into assessmentStatus (moduleID, weighting, assessmentName, assessmentReady,readyDate,intModerated)
    values (1, 60, 'secondAssessment', true, '2023-12-25', false);
insert into assessmentStatus (moduleID,weighting, assessmentName, assessmentReady, readyDate,intModerated, intModeratedDate,intModComments)
    values (2, 100, 'onlyAssessment', true, '2023-12-25', true, '2023-12-25', 'the assessment needs more work');
insert into assessmentStatus (moduleID,weighting, assessmentName)
    values (3, 100, 'onlyAssessmentforthis  module');