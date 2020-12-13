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
isEmpty _           = False

-- Complexity:
addFirst :: a -> DEQue a -> DEQue a
addFirst k (DEQ x y) = DEQ (k : x) y

-- Complexity:
addLast :: a -> DEQue a -> DEQue a
addLast k (DEQ x y) = DEQ x (k : y)

-- Complexity:
first :: DEQue a -> a
first (DEQ [] []) = error "Lista vacia"
first (DEQ [] k) = first (DEQ (reverse k2) k1)
  where
    l = length k
    i = (mod l 2)
    n = div l 2
    k1 = if i == 0 then take n k else take (n-1) k
    k2 = if i == 0 then drop n k else drop (n-1) k
first (DEQ x y) = head x

-- Complexity:
last :: DEQue a -> a
last (DEQ [] []) = error "Lista vacia"
last (DEQ k []) = last (DEQ k1 (reverse k2))
  where
    l = length k
    i = (mod l 2)
    n = div l 2
    k1 = if i == 0 then take n k else take (n-1) k
    k2 = if i == 0 then drop n k else drop (n-1) k
last (DEQ x y) = head y

-- Complexity:
deleteFirst :: DEQue a -> DEQue a
deleteFirst (DEQ [] []) = DEQ [] []
deleteFirst (DEQ [] k) = deleteFirst (DEQ (reverse k2) k1)
  where
    l = length k
    i = (mod l 2)
    n = div l 2
    k1 = if i == 0 then take n k else take (n-1) k
    k2 = if i == 0 then drop n k else drop (n-1) k
deleteFirst (DEQ (x:xs) y) = DEQ xs y

-- Complexity:
deleteLast :: DEQue a -> DEQue a
deleteLast (DEQ [] []) = DEQ [] []
deleteLast (DEQ k []) = deleteLast (DEQ k1 (reverse k2))
  where
    l = length k
    i = (mod l 2)
    n = div l 2
    k1 = if i == 0 then take n k else take (n-1) k
    k2 = if i == 0 then drop n k else drop (n-1) k
deleteLast (DEQ x (y:ys)) = DEQ x ys



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
