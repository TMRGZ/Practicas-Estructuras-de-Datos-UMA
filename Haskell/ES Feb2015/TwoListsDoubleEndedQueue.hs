-------------------------------------------------------------------------------
-- Estructuras de Datos. Grado en Informática, IS e IC. UMA.
-- Examen de Febrero 2015.
--
-- Implementación del TAD Deque
--
-- Apellidos:
-- Nombre:
-- Grado en Ingeniería ...
-- Grupo:
-- Número de PC:
-------------------------------------------------------------------------------

module TwoListsDoubleEndedQueue
   ( DEQue
   , empty
   , isEmpty
   , first
   , last
   , addFirst
   , addLast
   , deleteFirst
   , deleteLast
   ) where

import Prelude hiding (last)
import Data.List(intercalate)
import Test.QuickCheck

data DEQue a = DEQ [a] [a]

-- Complexity:
empty :: DEQue a
empty = DEQ [] []

-- Complexity:
isEmpty :: DEQue a -> Bool
isEmpty (DEQ [] []) = True
isEmpty (DEQ _ _) = False

-- Complexity:
addFirst :: a -> DEQue a -> DEQue a
addFirst x (DEQ xs ys) = (DEQ (x:xs) ys)

-- Complexity:
addLast :: a -> DEQue a -> DEQue a
addLast y (DEQ xs ys) = (DEQ xs (y:ys))

-- Complexity:
first :: DEQue a -> a
first (DEQ xs ys)
    | isEmpty (DEQ xs ys) = error "No bueno"
    | length xs == 0 = head (drop ((length ys)-1) ys)
    | otherwise = head xs

-- Complexity:
last :: DEQue a -> a
last (DEQ xs ys)
    | isEmpty (DEQ xs ys) = error "No bueno"
    | length ys == 0 = head (drop ((length xs)-1) xs)
    | otherwise = head ys

-- Complexity:
deleteFirst :: DEQue a -> DEQue a
deleteFirst (DEQ (x:xs) ys) = (DEQ xs ys)
deleteFirst (DEQ [] ys) = (DEQ izq der)
    where
        izq = reverse (drop (div (length ys) 2) ys)
        der = take (div (length ys) 2) ys

-- Complexity:
deleteLast :: DEQue a -> DEQue a
deleteLast (DEQ xs (y:ys)) = (DEQ xs ys)
deleteLast (DEQ xs []) = (DEQ izq der)
    where
        izq = reverse (drop (div (length xs) 2) xs)
        der = take (div (length xs) 2) xs



instance (Show a) => Show (DEQue a) where
   show q = "TwoListsDoubleEndedQueue(" ++ intercalate "," [show x | x <- toList q] ++ ")"

toList :: DEQue a -> [a]
toList (DEQ xs ys) =  xs ++ reverse ys

instance (Eq a) => Eq (DEQue a) where
   q == q' =  toList q == toList q'

instance (Arbitrary a) => Arbitrary (DEQue a) where
   arbitrary =  do
      xs <- listOf arbitrary
      ops <- listOf (oneof [return addFirst, return addLast])
      return (foldr id empty (zipWith ($) ops xs))
