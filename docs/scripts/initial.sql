/* Role użytkowników */
INSERT INTO Role(roleId, name) VALUES
(1, 'Admin'),
(2, 'Helpdesk Manager'),
(3, 'Helpdesk User'),
(4, 'Customer Manager'),
(5, 'Customer User');

/* Priorytety zgłoszeń */
INSERT INTO Priority(priorityId, name) VALUES
(1, 'Low'),
(2, 'Normal'),
(3, 'High'),
(4, 'Critical');

/* Statusy zgłoszeń */
INSERT INTO Status(statusId, name) VALUES
(1, 'Not Started'),
(2, 'In Progress'),
(3, 'Need More Information'),
(4, 'Ready For Upgrade'),
(5, 'Closed'),
(6, 'Reopened');

/* Typy notatek dla zgłoszeń */
INSERT INTO NoteType(noteTypeId, name) VALUES
(1, 'Public'),
(2, 'Private'),
(3, 'Upgrade - public'),
(4, 'Upgrade - private');

/* Typy logów dotyczących zgłoszeń */
INSERT INTO EventType(eventTypeId, name, isPublic) VALUES
(1, 'Created task', true),
(2, 'Started working over task', true),
(3, 'Stoped, need more information', true),
(4, 'Finished, ready for upgrade', true),
(5, 'Closed', true),
(6, 'Reopened', true);

/* Domyślne czasy dla dostępnych priorytetów */
INSERT INTO DefaultCustomerPriority(defaultCustomerPriorityId, executionDuration, priorityId) VALUES
(1, 10, 1),
(2, 5, 2),
(3, 4, 3),
(4, 2, 4);
