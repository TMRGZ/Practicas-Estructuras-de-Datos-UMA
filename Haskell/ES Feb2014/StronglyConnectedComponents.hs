-------------------------------------------------------------------------------
-- Localización de las componentes fuertemente conexas de un digrafo 
--
-- Estructuras de Datos. Grado en Informática. UMA.
-- 18 de Febrero 2014
--
-- Apellidos:                  Nombre:  
-- Grado en Ingeniería ....
-- Grupo:               Puesto:
-------------------------------------------------------------------------------

module StronglyConnectedComponents  where

import DataStructures.Graph.DiGraph 

import DataStructures.Graph.DiGraphDFT
    ( dft         -- :: (Ord a) => DiGraph a -> a -> [a] 
    )

import Data.List( (\\), intersect )

-------
-- A --
-------
reverseDiGraph :: Eq a => DiGraph a -> DiGraph a
reverseDiGraph g = mkDiGraphEdges (vertices g) [(v:->u) | u:->v <- diEdges g]

-------
-- B --
-------
restrictDiGraph :: Eq a => DiGraph a -> [a] -> DiGraph a
-- el subgrafo de g con vértices en vs
--restrictDiGraph g vs =  (mkDiGraphEdges [ v | v <- vs, elem v (vertices g)] [(u :-> v) | (u :-> v) <- (diEdges g), elem u vs, elem v vs])
restrictDiGraph g vs = deleteVertices' vs g


deleteVertices :: Eq a => [a] -> DiGraph a -> DiGraph a
deleteVertices xs g = mkDiGraphSuc (vertices g\\xs) suc' 
    where suc' v = (successors g v) \\ xs

deleteVertices' :: Eq a => [a] -> DiGraph a -> DiGraph a
deleteVertices' xs g = mkDiGraphSuc (intersect (vertices g) xs) suc' 
    where suc' v = intersect (successors g v) xs


{-
*StronglyConnectedComponents> restrictDiGraph gExample [A,B,H,I,J]
Graph, Vertices : [A,B,H], DiEdges: [A :-> B]

*StronglyConnectedComponents> reverseDiGraph $ restrictDiGraph gExample [A,B,H] 
Graph, Vertices : [A,B,H], DiEdges: [B :-> A]
-}

type SCC a = [a] 

-------
-- C --
-------
sccOf :: Ord a => DiGraph a -> a -> SCC a
-- la scc (strongly connected component) en el grafo g del vértice v
sccOf g v = sol
    where 
        vs = dft g v
        gr = restrictDiGraph g vs
        g' = reverseDiGraph gr
        sol = dft g' v

{-
*StronglyConnectedComponents> sccOf  gExample A
[A,E,B]
-}

-------
-- D --
-------
-- todas las componentes
sccs :: Ord a => DiGraph a -> [SCC a]
--sccs g  = [sccOf g v | v <- vertices g]
sccs g = sccsAux g (vertices g) []
--sccs g = foldr (\e s -> s ++ [(sccOf (deleteVertices s g) e)]) [] (vertices g)

sccsAux g [] sol = sol
sccsAux g (x:xs) sol = sccsAux g' (vertices g') ([sc] ++ sol)
        where
            sc = (sccOf g x)
            g' = deleteVertices sc g


{-
*StronglyConnectedComponents> sccs gExample
[[A,E,B],[C,D,H],[F,G]]
it :: [SCC Vertice]

-- las componentes son tres ciclos
-}


-------------------------------------
--- El grafo del enunciado del examen 
-------------------------------------

data Vertice = A|B|C|D|E|F|G|H|I|J|K deriving (Eq,Show,Ord,Enum)

{-
 A ->B      C <---> D
 ^  /|      |       ^
 | / |      |       |
 |/. v      v       v   (atención al arco B :-> E
 E -> F<--> G <---- H
-}
gExample = mkDiGraphEdges [A .. H] 
                         [ A :-> B, B:-> F, B:->E, C:->D, C:->G, 
                           D:->C, D:->H, E:->F, E:->A,
                           F:->G, G:->F, H:->D, H:->G]


