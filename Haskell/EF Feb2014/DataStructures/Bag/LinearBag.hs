-------------------------------------------------------------------------------
-- Linear implementation of Bags. Nodes are sorted 
--
-- Data Structures. Grado en Informática. UMA.
-- Pepe Gallardo, 2012
-------------------------------------------------------------------------------

module DataStructures.Stack.SortedLinearBag
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

-- Showing a bag
instance (Show a) => Show (Bag a) where
        show b = "LinearBag(" ++ intercalate "," (map show (elems b)) ++")"
          where
            elems Empty        = []
            elems (Node x n b) = replicate n x ++ elems b

-- bag equality
instance (Eq a) => Eq (Bag a) where
  Empty        == Empty            = True
  (Node x n b) == (Node x' n' b')  = x==x' && n==n' && b==b'
  _            == _                = False

-- This instace is used by QuickCheck to generate random bags
instance (Ord a, Arbitrary a) => Arbitrary (Bag a) where
    arbitrary = do
      xs <- listOf arbitrary
      return (foldr insert empty xs)
