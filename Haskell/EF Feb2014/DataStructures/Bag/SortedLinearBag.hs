-------------------------------------------------------------------------------
-- Linear implementation of Bag
--
-- Data Structures. Grado en Informática. UMA.
-- Pepe Gallardo, 2012
-------------------------------------------------------------------------------

module DataStructures.Stack.LinearBag
  ( Bag
  , empty
  , isEmpty
  , insert
  , delete
  , occurrences
  ) where

import Data.List(intercalate)
import Test.QuickCheck

data Bag a = Empty | Node a Int (Bag a)

empty :: Bag a
empty = Empty

isEmpty :: Bag a -> Bool
isEmpty Empty = True
isEmpty _     = False

insert :: (Ord a) => a -> Bag a -> Bag a
insert x Empty = Node x 1 Empty
insert x (Node y n b)
  | x<y        = Node x 1 (Node y n b)
  | x==y       = Node y (n+1) b
  | otherwise  = Node y n (insert x b)

occurrences :: (Ord a) => a -> Bag a -> Int
occurrences x Empty = 0
occurrences x (Node y n b)
  | x<y             = 0
  | x==y            = n
  | otherwise       = occurrences x b

delete :: (Ord a) => a -> Bag a -> Bag a
delete x Empty = Empty
delete x (Node y n b)
  | x<y        = Node y n b
  | x==y       = if n>1 then Node y (n-1) b else b
  | otherwise  = delete x b

instance (Show a) => Show (Bag a) where
        show b = "LinearBag(" ++ intercalate "," (map show (elems b)) ++")"
          where
            elems Empty        = []
            elems (Node x n b) = replicate n x ++ elems b


instance (Ord a, Arbitrary a) => Arbitrary (Bag a) where
    arbitrary = do
      xs <- listOf arbitrary
      return (foldr insert empty xs)
