--
-- PostgreSQL database dump
--

-- Started on 2010-10-17 21:38:12 VET

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

--
-- TOC entry 1834 (class 0 OID 0)
-- Dependencies: 1511
-- Name: laboratorio_seq; Type: SEQUENCE SET; Schema: public; Owner: labcontrol_user
--

SELECT pg_catalog.setval('laboratorio_seq', 3, true);


--
-- TOC entry 1835 (class 0 OID 0)
-- Dependencies: 1515
-- Name: profesores_seq; Type: SEQUENCE SET; Schema: public; Owner: labcontrol_user
--

SELECT pg_catalog.setval('profesores_seq', 3, true);


--
-- TOC entry 1828 (class 0 OID 24676)
-- Dependencies: 1517
-- Data for Name: turnos; Type: TABLE DATA; Schema: public; Owner: labcontrol_user
--

COPY turnos (turno_key, turno_description) FROM stdin;
Diurno	Turno de la mañana
Nocturno	Turno de la noche
\.


--
-- TOC entry 1823 (class 0 OID 24657)
-- Dependencies: 1510 1828
-- Data for Name: coordinaciones; Type: TABLE DATA; Schema: public; Owner: labcontrol_user
--

COPY coordinaciones (id, carrera, telefono, coordinador, turno) FROM stdin;
MAT-001	Matemáticas	1112234	Pedro Perez	Diurno
COM-002	Computación	2222222	Evander Palacios	Diurno
FIS-003	Física	2321122	Miguel Torres	Nocturno
ING-005	Ingeniería	333-2222	El Potro Álvarez	Diurno
\.


--
-- TOC entry 1824 (class 0 OID 24662)
-- Dependencies: 1512
-- Data for Name: laboratorios; Type: TABLE DATA; Schema: public; Owner: labcontrol_user
--

COPY laboratorios (id, nombre, capacidad) FROM stdin;
1	Laboratorio Este	14
2	Laboratorio Oeste	0
3	Manualidades	60
\.


--
-- TOC entry 1825 (class 0 OID 24665)
-- Dependencies: 1513 1823
-- Data for Name: materias; Type: TABLE DATA; Schema: public; Owner: labcontrol_user
--

COPY materias (id, nombre, coordinacion_id) FROM stdin;
COM-6001	Algoritmos Y Programación	COM-002
\.


--
-- TOC entry 1831 (class 0 OID 24731)
-- Dependencies: 1520
-- Data for Name: periodos; Type: TABLE DATA; Schema: public; Owner: labcontrol_user
--

COPY periodos (periodo_key, periodo_description) FROM stdin;
Trimestral	Turno por trimestre
\.


--
-- TOC entry 1826 (class 0 OID 24668)
-- Dependencies: 1514 1823 1828 1831
-- Data for Name: profesores; Type: TABLE DATA; Schema: public; Owner: labcontrol_user
--

COPY profesores (id, nombre, periodo, telefono, turno, coordinacion_id) FROM stdin;
1	Evander Palacios	Trimestral	888-2212	Diurno	COM-002
2	Paloma Noceado	Trimestral	800-9414	Nocturno	COM-002
\.


--
-- TOC entry 1827 (class 0 OID 24673)
-- Dependencies: 1516
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: labcontrol_user
--

COPY roles (rol) FROM stdin;
administrador
\.


--
-- TOC entry 1830 (class 0 OID 24682)
-- Dependencies: 1519
-- Data for Name: usuarios; Type: TABLE DATA; Schema: public; Owner: labcontrol_user
--

COPY usuarios (usuario, credencial) FROM stdin;
admin	admin
\.


--
-- TOC entry 1829 (class 0 OID 24679)
-- Dependencies: 1518 1827 1830
-- Data for Name: usuario_rol; Type: TABLE DATA; Schema: public; Owner: labcontrol_user
--

COPY usuario_rol (usuario, rol) FROM stdin;
admin	administrador
\.


-- Completed on 2010-10-17 21:38:12 VET

--
-- PostgreSQL database dump complete
--

