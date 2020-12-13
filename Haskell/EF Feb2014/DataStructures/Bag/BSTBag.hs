-------------------------------------------------------------------------------
-- Bags implemented with Binary Search Trees
--
-- Data Structures. Grado en Informática. UMA.
-- Pepe Gallardo, 2012
-------------------------------------------------------------------------------

module DataStructures.Bag.BSTBag 
  ( Bag
  , empty
  , insert
  , delete
  , occurrences
  ) where

import Data.Function(on)
import Data.List(intercalate)
import Test.QuickCheck
import qualified DataStructures.SearchTree.BST as T

-- A pair stores one element in the bag and its ocurrences
data Pair a = P a Int

key :: Pair a -> a
key (P k n) = k

cont :: Pair a -> Int
cont (P k n) = n

withKey :: a -> Pair a
withKey k = P k undefined

-- Pair are compared by using only their elements
instance (Eq a) => Eq (Pair a) where
  (==) = (==) `on` key

instance (Ord a) => Ord (Pair a) where
  compare = compare `on` key


newtype Bag a = B (T.BST (Pair a))

empty :: Bag a
empty = B T.empty

insert :: (Ord a) => a -> Bag a -> Bag a
insert x (B t) = B (T.mapOrAdd (\(P x n) -> P x (n+1)) (P x 1) t)

occurrences :: (Ord a) => a -> Bag a -> Int
occurrences x (B t) = 
  case T.search (withKey x) t of
    Nothing      -> 0
    Just (P x n) -> n

delete :: (Ord a) => a -> Bag a -> Bag a
delete x (B t) = B (T.mapOrAdd (\(P x n) -> P x (max 0 (n-1))) (P x 0) t)

-- Showing a bag
instance (Show a) => Show (Bag a) where
  show (B t)  = "BSTBag(" ++ intercalate "," (aux (T.inOrder t)) ++ ")"
   where
    aux []            = []
    aux (P v 0 : xs)  = aux xs 
    aux (P v c : xs)  = show v : aux (P v (c-1):xs)

-- bags equality
instance (Eq a) => Eq (Bag a) where
  (B t) == (B t') = T.inOrder t == T.inOrder t'

-- This instace is used by QuickCheck to generate random bags
instance (Arbitrary a, Ord a) => Arbitrary (Bag a) where
    arbitrary =  do
      xs <- listOf arbitrary
      return (foldr insert empty xs)
