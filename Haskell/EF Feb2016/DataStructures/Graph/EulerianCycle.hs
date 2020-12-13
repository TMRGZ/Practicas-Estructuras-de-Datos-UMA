-------------------------------------------------------------------------------
-- Student's name:
-- Student's group:
--
-- Data Structures. Grado en Informática. UMA.
-------------------------------------------------------------------------------

module DataStructures.Graph.EulerianCycle(isEulerian, eulerianCycle) where

import DataStructures.Graph.Graph
import Data.List

--H.1)
isEulerian :: Eq a => Graph a -> Bool
isEulerian g = not (isEmpty g)
isEulerian g = foldr (\e s -> s&&(esPar g e)) True (vertices g)

esPar g e = mod (degree g e) 2 == 0

-- H.2)
remove :: (Eq a) => Graph a -> (a,a) -> Graph a
remove g (v,u) = removeAux(deleteEdge g (v,u))
    
removeAux g = foldr(\e s -> if((degree g e) == 0) then (deleteVertex s e) else s) g (vertices g)

-- H.3)
extractCycle :: (Eq a) => Graph a -> a -> (Graph a, Path a)
extractCycle g v0 = extractAux g v0 [v0]

extractAux g v p
    | u == (head p) = (g', p')
    | otherwise     = extractAux g' u p'
        where
            g'  = remove g (v,u)
            u   = head (successors g v)
            p'  = p++[u]


-- H.4)
connectCycles :: (Eq a) => Path a -> Path a -> Path a
connectCycles [] (y:ys) = y:ys
connectCycles (x:xs) (y:ys)
    | x == y = x:ys++xs
    | otherwise = x:(connectCycles xs (y:ys))

-- H.5)
vertexInCommon :: Eq a => Graph a -> Path a -> a
vertexInCommon g cycle = vertexAux (vertices g) cycle

vertexAux :: Eq a => [a] -> [a] -> a
vertexAux (x:xs) y 
    | (elem x y) = x
    | otherwise = vertexAux xs y

-- H.6) 
eulerianCycle :: Eq a => Graph a -> Path a
eulerianCycle g 
    | isEulerian g = eulerianAux  g' p' 
    | otherwise = error "Grafo no bueno"
        where
            (g',p') = extractCycle g (head (vertices g))

eulerianAux :: Eq a => Graph a -> Path a -> Path a
eulerianAux g cycle 
    | isEmpty g = cycle
    | otherwise = eulerianAux g' (connectCycles cycle p')
        where
            (g',p') = extractCycle g (vertexInCommon g cycle)