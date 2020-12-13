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
isEulerian g = foldr (\e x -> x&&((mod (degree g e) 2) == 0)) True (vertices g)

-- H.2)
remove :: (Eq a) => Graph a -> (a,a) -> Graph a
remove g (v,u) = removeAux(deleteEdge g (v,u))


removeAux g = foldr (\e x -> if ((degree g e)==0) then (deleteVertex x e) else x) g (vertices g)

-- H.3)
extractCycle :: (Eq a) => Graph a -> a -> (Graph a, Path a)
extractCycle g v0 = extractAux g v0 [v0]

extractAux g v l
    | u == head l = (g', p')
    | otherwise = extractAux g' u p'
        where 
            g' = remove g (v,u)
            u = head (successors g v)
            p' = l++[u]
 

-- H.4)
connectCycles :: (Eq a) => Path a -> Path a -> Path a
connectCycles [] ys = ys
connectCycles (x:xs) (y:ys)
    | x == y = (x:ys)++xs
    | otherwise = x:(connectCycles xs (y:ys))

-- H.5)
vertexInCommon :: Eq a => Graph a -> Path a -> a
vertexInCommon g cycle = vertexAux (vertices g) cycle

vertexAux (x:xs) ys
    | elem x ys = x
    | otherwise = vertexAux xs ys

-- H.6) 
eulerianCycle :: Eq a => Graph a -> Path a
eulerianCycle g
    | isEulerian g = eulerianAux g' p'
    | otherwise = error "Grafo meh"
            where
                (g', p') = extractCycle g (head (vertices g))  

eulerianAux g cycle
    | isEmpty g = cycle
    | otherwise = eulerianAux g' (connectCycles cycle p')
        where 
            (g', p') = extractCycle g (vertexInCommon g cycle)
