--
-- PostgreSQL database dump
--

\restrict fxDWIOF6nuueelx8iQd0TdV2HljZ4GMKfZrCk71hirzfzRtjRps9Zk24K8BJkY0

-- Dumped from database version 18.0
-- Dumped by pg_dump version 18.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: cliente_juridico; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cliente_juridico (
    id_cj integer NOT NULL,
    ruc character(13) NOT NULL,
    razon_social character varying(100) NOT NULL,
    direccion_cj character varying(200) NOT NULL,
    correo_cj character varying(100) NOT NULL,
    telefono_cj character(10) NOT NULL,
    estado_cj character(1) NOT NULL
);


ALTER TABLE public.cliente_juridico OWNER TO postgres;

--
-- Name: cliente_juridico_id_cj_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cliente_juridico_id_cj_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.cliente_juridico_id_cj_seq OWNER TO postgres;

--
-- Name: cliente_juridico_id_cj_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cliente_juridico_id_cj_seq OWNED BY public.cliente_juridico.id_cj;


--
-- Name: cliente_natural; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cliente_natural (
    id_cn integer NOT NULL,
    cedula_cn character(10) NOT NULL,
    nombre_cn character varying(100) NOT NULL,
    direccion_cn character varying(100) NOT NULL,
    correo_cn character varying(100) NOT NULL,
    telefono_cn character(10) NOT NULL,
    fecha_cn date NOT NULL,
    estado_cn character(1) NOT NULL
);


ALTER TABLE public.cliente_natural OWNER TO postgres;

--
-- Name: cliente_natural_id_cn_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cliente_natural_id_cn_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.cliente_natural_id_cn_seq OWNER TO postgres;

--
-- Name: cliente_natural_id_cn_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cliente_natural_id_cn_seq OWNED BY public.cliente_natural.id_cn;


--
-- Name: cotejo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cotejo (
    id_ct integer NOT NULL,
    id_fc integer,
    id_lote integer,
    cantidad integer NOT NULL
);


ALTER TABLE public.cotejo OWNER TO postgres;

--
-- Name: cotejo_id_ct_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cotejo_id_ct_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.cotejo_id_ct_seq OWNER TO postgres;

--
-- Name: cotejo_id_ct_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cotejo_id_ct_seq OWNED BY public.cotejo.id_ct;


--
-- Name: detalle_factura; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.detalle_factura (
    id_fv integer NOT NULL,
    codigo_barras character(13) NOT NULL,
    cantidad integer NOT NULL,
    precio_vt numeric NOT NULL,
    subtotal_p numeric NOT NULL,
    iva_df numeric NOT NULL,
    descuento numeric NOT NULL
);


ALTER TABLE public.detalle_factura OWNER TO postgres;

--
-- Name: detalle_pedido; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.detalle_pedido (
    id_pedido integer NOT NULL,
    codigo_barras character(13) NOT NULL,
    cantidad integer NOT NULL
);


ALTER TABLE public.detalle_pedido OWNER TO postgres;

--
-- Name: factura_c; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.factura_c (
    id_fc integer NOT NULL,
    num_fc character varying(50) NOT NULL,
    estado character(1) NOT NULL,
    fecha_fc date NOT NULL,
    fue_ingresada boolean,
    id_prove integer
);


ALTER TABLE public.factura_c OWNER TO postgres;

--
-- Name: factura_c_id_fc_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.factura_c_id_fc_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.factura_c_id_fc_seq OWNER TO postgres;

--
-- Name: factura_c_id_fc_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.factura_c_id_fc_seq OWNED BY public.factura_c.id_fc;


--
-- Name: factura_v; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.factura_v (
    id_fv integer NOT NULL,
    id_cn integer,
    id_cj integer,
    id_pago integer,
    id_global integer,
    tipo_cl character(1) NOT NULL,
    fecha_em date NOT NULL,
    subtotal_fv numeric NOT NULL,
    iva numeric NOT NULL,
    total_fv numeric NOT NULL,
    descuento_fv numeric NOT NULL,
    estado character(1) NOT NULL,
    id_firma integer NOT NULL
);


ALTER TABLE public.factura_v OWNER TO postgres;

--
-- Name: factura_v_id_fv_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.factura_v_id_fv_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.factura_v_id_fv_seq OWNER TO postgres;

--
-- Name: factura_v_id_fv_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.factura_v_id_fv_seq OWNED BY public.factura_v.id_fv;


--
-- Name: firma; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.firma (
    id_firma integer NOT NULL,
    ruta_fr character varying(256) NOT NULL
);


ALTER TABLE public.firma OWNER TO postgres;

--
-- Name: firma_id_firma_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.firma_id_firma_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.firma_id_firma_seq OWNER TO postgres;

--
-- Name: firma_id_firma_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.firma_id_firma_seq OWNED BY public.firma.id_firma;


--
-- Name: indice_terapeutico; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.indice_terapeutico (
    id_indice_terapeutico integer NOT NULL,
    nombre_indice character varying(100) NOT NULL
);


ALTER TABLE public.indice_terapeutico OWNER TO postgres;

--
-- Name: indice_terapeutico_id_indice_terapeutico_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.indice_terapeutico ALTER COLUMN id_indice_terapeutico ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.indice_terapeutico_id_indice_terapeutico_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: iva; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.iva (
    iva_valor numeric NOT NULL
);


ALTER TABLE public.iva OWNER TO postgres;

--
-- Name: kardex; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.kardex (
    numero_kardex character(6) NOT NULL,
    fecha_generacion date NOT NULL,
    estado character(2) NOT NULL,
    dinero_sobrante double precision NOT NULL,
    dinero_faltante double precision NOT NULL,
    total_dinero double precision NOT NULL
);


ALTER TABLE public.kardex OWNER TO postgres;

--
-- Name: kardex_detalle; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.kardex_detalle (
    numero_kardex character(6) NOT NULL,
    id_lote integer NOT NULL,
    cajas_existentes integer NOT NULL,
    unidades_existentes integer NOT NULL,
    cajas_encontradas integer NOT NULL,
    unidades_encontradas integer NOT NULL
);


ALTER TABLE public.kardex_detalle OWNER TO postgres;

--
-- Name: laboratorio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.laboratorio (
    id_lab integer NOT NULL,
    nombre_lab character varying(50) NOT NULL
);


ALTER TABLE public.laboratorio OWNER TO postgres;

--
-- Name: laboratorio_id_lab_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.laboratorio_id_lab_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.laboratorio_id_lab_seq OWNER TO postgres;

--
-- Name: laboratorio_id_lab_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.laboratorio_id_lab_seq OWNED BY public.laboratorio.id_lab;


--
-- Name: lote; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.lote (
    id_lote integer NOT NULL,
    codigo_barras character(13),
    num_lote character varying(12) NOT NULL,
    stock integer NOT NULL,
    fecha_vn date NOT NULL,
    precio_compra numeric NOT NULL,
    rentabilidad integer NOT NULL,
    tamano_caja integer NOT NULL,
    estado character(1) NOT NULL,
    tiene_iva boolean NOT NULL
);


ALTER TABLE public.lote OWNER TO postgres;

--
-- Name: lote_id_lote_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.lote_id_lote_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.lote_id_lote_seq OWNER TO postgres;

--
-- Name: lote_id_lote_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.lote_id_lote_seq OWNED BY public.lote.id_lote;


--
-- Name: metas_laboratorio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.metas_laboratorio (
    id_ml integer NOT NULL,
    id_lab integer,
    metas integer NOT NULL,
    incentivo character varying(50) NOT NULL,
    progreso_lb integer NOT NULL,
    fecha_in_lab date NOT NULL,
    fecha_fn_lab date NOT NULL
);


ALTER TABLE public.metas_laboratorio OWNER TO postgres;

--
-- Name: metas_laboratorio_id_ml_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.metas_laboratorio_id_ml_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.metas_laboratorio_id_ml_seq OWNER TO postgres;

--
-- Name: metas_laboratorio_id_ml_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.metas_laboratorio_id_ml_seq OWNED BY public.metas_laboratorio.id_ml;


--
-- Name: metas_productos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.metas_productos (
    id_mp integer NOT NULL,
    codigo_barras character(13),
    meta integer NOT NULL,
    incentivo character varying(50) NOT NULL,
    progreso_lb integer NOT NULL,
    fecha_in_p date NOT NULL,
    fecha_fn_p date NOT NULL
);


ALTER TABLE public.metas_productos OWNER TO postgres;

--
-- Name: metas_productos_id_mp_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.metas_productos_id_mp_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.metas_productos_id_mp_seq OWNER TO postgres;

--
-- Name: metas_productos_id_mp_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.metas_productos_id_mp_seq OWNED BY public.metas_productos.id_mp;


--
-- Name: metodo_pago; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.metodo_pago (
    id_pago integer NOT NULL,
    nombre_pago character varying(30) NOT NULL,
    estado_pago character(1) NOT NULL
);


ALTER TABLE public.metodo_pago OWNER TO postgres;

--
-- Name: metodo_pago_id_pago_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.metodo_pago_id_pago_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.metodo_pago_id_pago_seq OWNER TO postgres;

--
-- Name: metodo_pago_id_pago_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.metodo_pago_id_pago_seq OWNED BY public.metodo_pago.id_pago;


--
-- Name: pedido_pv; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pedido_pv (
    id_pedido integer NOT NULL,
    id_prove integer,
    estado character(1) NOT NULL,
    fecha_pv date NOT NULL
);


ALTER TABLE public.pedido_pv OWNER TO postgres;

--
-- Name: pedido_pv_id_pedido_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pedido_pv_id_pedido_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.pedido_pv_id_pedido_seq OWNER TO postgres;

--
-- Name: pedido_pv_id_pedido_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pedido_pv_id_pedido_seq OWNED BY public.pedido_pv.id_pedido;


--
-- Name: producto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.producto (
    codigo_barras character(13) NOT NULL,
    id_lab integer,
    codigo_aux character varying(15),
    nombre_p character varying(150) NOT NULL,
    descripcion character varying(200) NOT NULL,
    categoria character(1) NOT NULL,
    forma_venta character(1) NOT NULL,
    tipo_venta character(1) NOT NULL,
    pvp numeric NOT NULL,
    id_indice_t integer,
    fecha_uv date,
    tiene_iva boolean
);


ALTER TABLE public.producto OWNER TO postgres;

--
-- Name: promo_global; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.promo_global (
    id_global integer NOT NULL,
    promo_global numeric NOT NULL,
    fecha_in_pg date NOT NULL,
    fecha_fn_pg date NOT NULL
);


ALTER TABLE public.promo_global OWNER TO postgres;

--
-- Name: promo_global_id_global_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.promo_global_id_global_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.promo_global_id_global_seq OWNER TO postgres;

--
-- Name: promo_global_id_global_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.promo_global_id_global_seq OWNED BY public.promo_global.id_global;


--
-- Name: promo_producto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.promo_producto (
    id_promo integer NOT NULL,
    codigo_barras character(13),
    porcen_desc integer NOT NULL,
    fecha_in_pp date NOT NULL,
    fecha_fn_pp date NOT NULL
);


ALTER TABLE public.promo_producto OWNER TO postgres;

--
-- Name: promo_producto_id_promo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.promo_producto_id_promo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.promo_producto_id_promo_seq OWNER TO postgres;

--
-- Name: promo_producto_id_promo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.promo_producto_id_promo_seq OWNED BY public.promo_producto.id_promo;


--
-- Name: proveedores; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.proveedores (
    id_prove integer NOT NULL,
    nombre_pro character varying(100) NOT NULL,
    telefono_pro character(10) NOT NULL,
    correo_pro character varying(100) NOT NULL,
    estado_pv character(1) CONSTRAINT proveedores_estad_pv_not_null NOT NULL
);


ALTER TABLE public.proveedores OWNER TO postgres;

--
-- Name: proveedores_id_prove_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.proveedores_id_prove_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.proveedores_id_prove_seq OWNER TO postgres;

--
-- Name: proveedores_id_prove_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.proveedores_id_prove_seq OWNED BY public.proveedores.id_prove;


--
-- Name: usuarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuarios (
    id_usuario integer NOT NULL,
    nombre_us character varying(50) NOT NULL,
    correo_us character varying(50) NOT NULL,
    password_us character varying(50) NOT NULL,
    perfil_us character(2) NOT NULL
);


ALTER TABLE public.usuarios OWNER TO postgres;

--
-- Name: usuarios_id_usuario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuarios_id_usuario_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.usuarios_id_usuario_seq OWNER TO postgres;

--
-- Name: usuarios_id_usuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuarios_id_usuario_seq OWNED BY public.usuarios.id_usuario;


--
-- Name: cliente_juridico id_cj; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente_juridico ALTER COLUMN id_cj SET DEFAULT nextval('public.cliente_juridico_id_cj_seq'::regclass);


--
-- Name: cliente_natural id_cn; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente_natural ALTER COLUMN id_cn SET DEFAULT nextval('public.cliente_natural_id_cn_seq'::regclass);


--
-- Name: cotejo id_ct; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cotejo ALTER COLUMN id_ct SET DEFAULT nextval('public.cotejo_id_ct_seq'::regclass);


--
-- Name: factura_c id_fc; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.factura_c ALTER COLUMN id_fc SET DEFAULT nextval('public.factura_c_id_fc_seq'::regclass);


--
-- Name: factura_v id_fv; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.factura_v ALTER COLUMN id_fv SET DEFAULT nextval('public.factura_v_id_fv_seq'::regclass);


--
-- Name: firma id_firma; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.firma ALTER COLUMN id_firma SET DEFAULT nextval('public.firma_id_firma_seq'::regclass);


--
-- Name: laboratorio id_lab; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.laboratorio ALTER COLUMN id_lab SET DEFAULT nextval('public.laboratorio_id_lab_seq'::regclass);


--
-- Name: lote id_lote; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lote ALTER COLUMN id_lote SET DEFAULT nextval('public.lote_id_lote_seq'::regclass);


--
-- Name: metas_laboratorio id_ml; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.metas_laboratorio ALTER COLUMN id_ml SET DEFAULT nextval('public.metas_laboratorio_id_ml_seq'::regclass);


--
-- Name: metas_productos id_mp; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.metas_productos ALTER COLUMN id_mp SET DEFAULT nextval('public.metas_productos_id_mp_seq'::regclass);


--
-- Name: metodo_pago id_pago; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.metodo_pago ALTER COLUMN id_pago SET DEFAULT nextval('public.metodo_pago_id_pago_seq'::regclass);


--
-- Name: pedido_pv id_pedido; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedido_pv ALTER COLUMN id_pedido SET DEFAULT nextval('public.pedido_pv_id_pedido_seq'::regclass);


--
-- Name: promo_global id_global; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.promo_global ALTER COLUMN id_global SET DEFAULT nextval('public.promo_global_id_global_seq'::regclass);


--
-- Name: promo_producto id_promo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.promo_producto ALTER COLUMN id_promo SET DEFAULT nextval('public.promo_producto_id_promo_seq'::regclass);


--
-- Name: proveedores id_prove; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.proveedores ALTER COLUMN id_prove SET DEFAULT nextval('public.proveedores_id_prove_seq'::regclass);


--
-- Name: usuarios id_usuario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios ALTER COLUMN id_usuario SET DEFAULT nextval('public.usuarios_id_usuario_seq'::regclass);


--
-- Data for Name: cliente_juridico; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cliente_juridico (id_cj, ruc, razon_social, direccion_cj, correo_cj, telefono_cj, estado_cj) FROM stdin;
\.


--
-- Data for Name: cliente_natural; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cliente_natural (id_cn, cedula_cn, nombre_cn, direccion_cn, correo_cn, telefono_cn, fecha_cn, estado_cn) FROM stdin;
\.


--
-- Data for Name: cotejo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cotejo (id_ct, id_fc, id_lote, cantidad) FROM stdin;
\.


--
-- Data for Name: detalle_factura; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.detalle_factura (id_fv, codigo_barras, cantidad, precio_vt, subtotal_p, iva_df, descuento) FROM stdin;
\.


--
-- Data for Name: detalle_pedido; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.detalle_pedido (id_pedido, codigo_barras, cantidad) FROM stdin;
\.


--
-- Data for Name: factura_c; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.factura_c (id_fc, num_fc, estado, fecha_fc, fue_ingresada, id_prove) FROM stdin;
\.


--
-- Data for Name: factura_v; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.factura_v (id_fv, id_cn, id_cj, id_pago, id_global, tipo_cl, fecha_em, subtotal_fv, iva, total_fv, descuento_fv, estado, id_firma) FROM stdin;
\.


--
-- Data for Name: firma; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.firma (id_firma, ruta_fr) FROM stdin;
\.


--
-- Data for Name: indice_terapeutico; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.indice_terapeutico (id_indice_terapeutico, nombre_indice) FROM stdin;
\.


--
-- Data for Name: iva; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.iva (iva_valor) FROM stdin;
\.


--
-- Data for Name: kardex; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.kardex (numero_kardex, fecha_generacion, estado, dinero_sobrante, dinero_faltante, total_dinero) FROM stdin;
\.


--
-- Data for Name: kardex_detalle; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.kardex_detalle (numero_kardex, id_lote, cajas_existentes, unidades_existentes, cajas_encontradas, unidades_encontradas) FROM stdin;
\.


--
-- Data for Name: laboratorio; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.laboratorio (id_lab, nombre_lab) FROM stdin;
\.


--
-- Data for Name: lote; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.lote (id_lote, codigo_barras, num_lote, stock, fecha_vn, precio_compra, rentabilidad, tamano_caja, estado, tiene_iva) FROM stdin;
\.


--
-- Data for Name: metas_laboratorio; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.metas_laboratorio (id_ml, id_lab, metas, incentivo, progreso_lb, fecha_in_lab, fecha_fn_lab) FROM stdin;
\.


--
-- Data for Name: metas_productos; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.metas_productos (id_mp, codigo_barras, meta, incentivo, progreso_lb, fecha_in_p, fecha_fn_p) FROM stdin;
\.


--
-- Data for Name: metodo_pago; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.metodo_pago (id_pago, nombre_pago, estado_pago) FROM stdin;
\.


--
-- Data for Name: pedido_pv; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pedido_pv (id_pedido, id_prove, estado, fecha_pv) FROM stdin;
\.


--
-- Data for Name: producto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.producto (codigo_barras, id_lab, codigo_aux, nombre_p, descripcion, categoria, forma_venta, tipo_venta, pvp, id_indice_t, fecha_uv, tiene_iva) FROM stdin;
\.


--
-- Data for Name: promo_global; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.promo_global (id_global, promo_global, fecha_in_pg, fecha_fn_pg) FROM stdin;
\.


--
-- Data for Name: promo_producto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.promo_producto (id_promo, codigo_barras, porcen_desc, fecha_in_pp, fecha_fn_pp) FROM stdin;
\.


--
-- Data for Name: proveedores; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.proveedores (id_prove, nombre_pro, telefono_pro, correo_pro, estado_pv) FROM stdin;
\.


--
-- Data for Name: usuarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuarios (id_usuario, nombre_us, correo_us, password_us, perfil_us) FROM stdin;
\.


--
-- Name: cliente_juridico_id_cj_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cliente_juridico_id_cj_seq', 1, false);


--
-- Name: cliente_natural_id_cn_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cliente_natural_id_cn_seq', 1, false);


--
-- Name: cotejo_id_ct_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cotejo_id_ct_seq', 1, false);


--
-- Name: factura_c_id_fc_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.factura_c_id_fc_seq', 1, false);


--
-- Name: factura_v_id_fv_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.factura_v_id_fv_seq', 1, false);


--
-- Name: firma_id_firma_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.firma_id_firma_seq', 1, false);


--
-- Name: indice_terapeutico_id_indice_terapeutico_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.indice_terapeutico_id_indice_terapeutico_seq', 1, false);


--
-- Name: laboratorio_id_lab_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.laboratorio_id_lab_seq', 1, false);


--
-- Name: lote_id_lote_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.lote_id_lote_seq', 1, false);


--
-- Name: metas_laboratorio_id_ml_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.metas_laboratorio_id_ml_seq', 1, false);


--
-- Name: metas_productos_id_mp_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.metas_productos_id_mp_seq', 1, false);


--
-- Name: metodo_pago_id_pago_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.metodo_pago_id_pago_seq', 1, false);


--
-- Name: pedido_pv_id_pedido_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.pedido_pv_id_pedido_seq', 1, false);


--
-- Name: promo_global_id_global_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.promo_global_id_global_seq', 1, false);


--
-- Name: promo_producto_id_promo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.promo_producto_id_promo_seq', 1, false);


--
-- Name: proveedores_id_prove_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.proveedores_id_prove_seq', 1, false);


--
-- Name: usuarios_id_usuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuarios_id_usuario_seq', 1, false);


--
-- Name: indice_terapeutico indice_terapeutico_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.indice_terapeutico
    ADD CONSTRAINT indice_terapeutico_pkey PRIMARY KEY (id_indice_terapeutico);


--
-- Name: cliente_juridico pk_cliente_juridico; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente_juridico
    ADD CONSTRAINT pk_cliente_juridico PRIMARY KEY (id_cj);


--
-- Name: cliente_natural pk_cliente_natural; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente_natural
    ADD CONSTRAINT pk_cliente_natural PRIMARY KEY (id_cn);


--
-- Name: cotejo pk_cotejo; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cotejo
    ADD CONSTRAINT pk_cotejo PRIMARY KEY (id_ct);


--
-- Name: detalle_factura pk_detalle_factura; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_factura
    ADD CONSTRAINT pk_detalle_factura PRIMARY KEY (id_fv, codigo_barras);


--
-- Name: detalle_pedido pk_detalle_pedido; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_pedido
    ADD CONSTRAINT pk_detalle_pedido PRIMARY KEY (id_pedido, codigo_barras);


--
-- Name: factura_c pk_factura_c; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.factura_c
    ADD CONSTRAINT pk_factura_c PRIMARY KEY (id_fc);


--
-- Name: factura_v pk_factura_v; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.factura_v
    ADD CONSTRAINT pk_factura_v PRIMARY KEY (id_fv);


--
-- Name: firma pk_firma; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.firma
    ADD CONSTRAINT pk_firma PRIMARY KEY (id_firma);


--
-- Name: kardex pk_kardex; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kardex
    ADD CONSTRAINT pk_kardex PRIMARY KEY (numero_kardex);


--
-- Name: kardex_detalle pk_kardex_detalle; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kardex_detalle
    ADD CONSTRAINT pk_kardex_detalle PRIMARY KEY (numero_kardex, id_lote);


--
-- Name: laboratorio pk_laboratorio; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.laboratorio
    ADD CONSTRAINT pk_laboratorio PRIMARY KEY (id_lab);


--
-- Name: lote pk_lote; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lote
    ADD CONSTRAINT pk_lote PRIMARY KEY (id_lote);


--
-- Name: metas_laboratorio pk_metas_laboratorio; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.metas_laboratorio
    ADD CONSTRAINT pk_metas_laboratorio PRIMARY KEY (id_ml);


--
-- Name: metas_productos pk_metas_productos; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.metas_productos
    ADD CONSTRAINT pk_metas_productos PRIMARY KEY (id_mp);


--
-- Name: metodo_pago pk_metodo_pago; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.metodo_pago
    ADD CONSTRAINT pk_metodo_pago PRIMARY KEY (id_pago);


--
-- Name: pedido_pv pk_pedido_pv; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedido_pv
    ADD CONSTRAINT pk_pedido_pv PRIMARY KEY (id_pedido);


--
-- Name: producto pk_producto; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.producto
    ADD CONSTRAINT pk_producto PRIMARY KEY (codigo_barras);


--
-- Name: promo_global pk_promo_global; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.promo_global
    ADD CONSTRAINT pk_promo_global PRIMARY KEY (id_global);


--
-- Name: promo_producto pk_promo_producto; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.promo_producto
    ADD CONSTRAINT pk_promo_producto PRIMARY KEY (id_promo);


--
-- Name: proveedores pk_proveedores; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.proveedores
    ADD CONSTRAINT pk_proveedores PRIMARY KEY (id_prove);


--
-- Name: usuarios pk_usuarios; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT pk_usuarios PRIMARY KEY (id_usuario);


--
-- Name: cotejo fk_cotejo_relations_factura_; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cotejo
    ADD CONSTRAINT fk_cotejo_relations_factura_ FOREIGN KEY (id_fc) REFERENCES public.factura_c(id_fc) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: cotejo fk_cotejo_relations_lote; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cotejo
    ADD CONSTRAINT fk_cotejo_relations_lote FOREIGN KEY (id_lote) REFERENCES public.lote(id_lote) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: detalle_factura fk_detalle__detalle_f_factura_; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_factura
    ADD CONSTRAINT fk_detalle__detalle_f_factura_ FOREIGN KEY (id_fv) REFERENCES public.factura_v(id_fv) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: detalle_factura fk_detalle__detalle_f_producto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_factura
    ADD CONSTRAINT fk_detalle__detalle_f_producto FOREIGN KEY (codigo_barras) REFERENCES public.producto(codigo_barras) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: detalle_pedido fk_detalle__detalle_p_pedido_p; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_pedido
    ADD CONSTRAINT fk_detalle__detalle_p_pedido_p FOREIGN KEY (id_pedido) REFERENCES public.pedido_pv(id_pedido) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: detalle_pedido fk_detalle__detalle_p_producto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.detalle_pedido
    ADD CONSTRAINT fk_detalle__detalle_p_producto FOREIGN KEY (codigo_barras) REFERENCES public.producto(codigo_barras) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: factura_v fk_factura__relations_firma; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.factura_v
    ADD CONSTRAINT fk_factura__relations_firma FOREIGN KEY (id_firma) REFERENCES public.firma(id_firma) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: factura_v fk_factura__relations_juridico; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.factura_v
    ADD CONSTRAINT fk_factura__relations_juridico FOREIGN KEY (id_cj) REFERENCES public.cliente_juridico(id_cj) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: factura_v fk_factura__relations_metodo_p; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.factura_v
    ADD CONSTRAINT fk_factura__relations_metodo_p FOREIGN KEY (id_pago) REFERENCES public.metodo_pago(id_pago) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: factura_v fk_factura__relations_natural; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.factura_v
    ADD CONSTRAINT fk_factura__relations_natural FOREIGN KEY (id_cn) REFERENCES public.cliente_natural(id_cn) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: factura_v fk_factura__relations_promo_gl; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.factura_v
    ADD CONSTRAINT fk_factura__relations_promo_gl FOREIGN KEY (id_global) REFERENCES public.promo_global(id_global) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: factura_c fk_factura_proveedor; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.factura_c
    ADD CONSTRAINT fk_factura_proveedor FOREIGN KEY (id_prove) REFERENCES public.proveedores(id_prove) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: kardex_detalle fk_kardex_detalle_kardex; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kardex_detalle
    ADD CONSTRAINT fk_kardex_detalle_kardex FOREIGN KEY (numero_kardex) REFERENCES public.kardex(numero_kardex) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: kardex_detalle fk_kardex_detalle_lote; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.kardex_detalle
    ADD CONSTRAINT fk_kardex_detalle_lote FOREIGN KEY (id_lote) REFERENCES public.lote(id_lote) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: lote fk_lote_relations_producto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lote
    ADD CONSTRAINT fk_lote_relations_producto FOREIGN KEY (codigo_barras) REFERENCES public.producto(codigo_barras) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: metas_laboratorio fk_metas_la_relations_laborato; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.metas_laboratorio
    ADD CONSTRAINT fk_metas_la_relations_laborato FOREIGN KEY (id_lab) REFERENCES public.laboratorio(id_lab) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: metas_productos fk_metas_pr_relations_producto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.metas_productos
    ADD CONSTRAINT fk_metas_pr_relations_producto FOREIGN KEY (codigo_barras) REFERENCES public.producto(codigo_barras) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: pedido_pv fk_pedido_p_relations_proveedo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedido_pv
    ADD CONSTRAINT fk_pedido_p_relations_proveedo FOREIGN KEY (id_prove) REFERENCES public.proveedores(id_prove) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: producto fk_producto_indice; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.producto
    ADD CONSTRAINT fk_producto_indice FOREIGN KEY (id_indice_t) REFERENCES public.indice_terapeutico(id_indice_terapeutico);


--
-- Name: producto fk_producto_relations_laborato; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.producto
    ADD CONSTRAINT fk_producto_relations_laborato FOREIGN KEY (id_lab) REFERENCES public.laboratorio(id_lab) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: promo_producto fk_promo_pr_relations_producto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.promo_producto
    ADD CONSTRAINT fk_promo_pr_relations_producto FOREIGN KEY (codigo_barras) REFERENCES public.producto(codigo_barras) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- PostgreSQL database dump complete
--

\unrestrict fxDWIOF6nuueelx8iQd0TdV2HljZ4GMKfZrCk71hirzfzRtjRps9Zk24K8BJkY0

