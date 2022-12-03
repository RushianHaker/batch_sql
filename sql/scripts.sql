
CREATE TABLE public.cards (
            id int4 NOT NULL,
            "name" varchar NOT NULL,
            full_name varchar NOT NULL
);

SELECT id, "name", full_name FROM public.cards;

INSERT INTO public.cards (id, "name", full_name) VALUES(1, 'test', 'test');
