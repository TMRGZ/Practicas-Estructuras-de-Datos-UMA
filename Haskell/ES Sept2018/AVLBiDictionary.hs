-------------------------------------------------------------------------------
-- Apellidos, Nombre:
-- Titulacion, Grupo:
--
-- Estructuras de Datos. Grados en Informatica. UMA.
-------------------------------------------------------------------------------

module AVLBiDictionary( BiDictionary
                      , empty
                      , isEmpty
                      , size
                      , insert
                      , valueOf
                      , keyOf
                      , deleteByKey
                      , deleteByValue
                      , toBiDictionary
                      , compose
                      , isPermutation
                      , orbitOf
                      , cyclesOf
                      ) where

import qualified DataStructures.Dictionary.AVLDictionary as D
import qualified DataStructures.Set.BSTSet               as S

import           Data.List                               (intercalate, nub,
                                                          (\\))
import           Data.Maybe                              (fromJust, fromMaybe,
                                                          isJust)
import           Test.QuickCheck


data BiDictionary a b = Bi (D.Dictionary a b) (D.Dictionary b a)

-- | Exercise a. empty, isEmpty, size

empty :: (Ord a, Ord b) => BiDictionary a b
empty = (Bi D.empty D.empty)

isEmpty :: (Ord a, Ord b) => BiDictionary a b -> Bool
isEmpty (Bi d1 d2) = (D.isEmpty d1) && (D.isEmpty d2)

size :: (Ord a, Ord b) => BiDictionary a b -> Int
size (Bi d1 d2) = D.size d1

-- | Exercise b. insert

insert :: (Ord a, Ord b) => a -> b -> BiDictionary a b -> BiDictionary a b
insert k v bi@(Bi dk dv) = (Bi (D.insert k v dk') (D.insert v k dv'))
    where (Bi dk' dv') = (deleteByKey k (deleteByValue v bi))

-- | Exercise c. valueOf

valueOf :: (Ord a, Ord b) => a -> BiDictionary a b -> Maybe b
valueOf x (Bi dk dv) = D.valueOf x dk

-- | Exercise d. keyOf

keyOf :: (Ord a, Ord b) => b -> BiDictionary a b -> Maybe a
keyOf x (Bi dk dv) = valueOf x (Bi dv dk)

-- | Exercise e. deleteByKey

deleteByKey :: (Ord a, Ord b) => a -> BiDictionary a b -> BiDictionary a b
deleteByKey x (Bi dk dv) = (toBiDictionary (D.delete x dk))

-- | Exercise f. deleteByValue

deleteByValue :: (Ord a, Ord b) => b -> BiDictionary a b -> BiDictionary a b
deleteByValue x (Bi dk dv) = (Bi dicBorradoInv dicBorrado)
  where
    dicBorrado    = (D.delete x dv)
    dicBorradoInv = foldr (\(ek, ev) s -> (D.insert ev ek s)) D.empty (D.keysValues dicBorrado)
-- | Exercise g. toBiDictionary

toBiDictionary :: (Ord a, Ord b) => D.Dictionary a b -> BiDictionary a b
toBiDictionary dic
  | not (length (D.values dic) == length (nub(D.values dic))) = error "No inyectivo"
  | otherwise = (Bi dic invDic)
    where
      invDic = foldr (\(ek, ev) s -> (D.insert ev ek s)) D.empty (D.keysValues dic)

-- | Exercise h. compose

compose :: (Ord a, Ord b, Ord c) => BiDictionary a b -> BiDictionary b c -> BiDictionary a c
compose b1@(Bi dk1 dv1) b2@(Bi dk2 dv2) = foldr (\e s -> (insert e (fromJust(D.valueOf (fromJust(D.valueOf e dk1)) dk2)) s)) empty keys
  where
    keys = [k | k <- D.keys dk1, elem (fromJust(D.valueOf k dk1)) (D.keys dk2)]




-- | Exercise i. isPermutation

isPermutation :: Ord a => BiDictionary a a -> Bool
isPermutation (Bi dk dv) = l == length nListaNoRep
  where
    l = length (D.keys dk)
    nLista = (D.keys dk) ++ (D.keys dv)
    nListaNoRep = nub nLista


-- |------------------------------------------------------------------------


-- | Exercise j. orbitOf

orbitOf :: Ord a => a -> BiDictionary a a -> [a]
orbitOf = undefined

-- | Exercise k. cyclesOf

cyclesOf :: Ord a => BiDictionary a a -> [[a]]
cyclesOf = undefined

-- |------------------------------------------------------------------------


instance (Show a, Show b) => Show (BiDictionary a b) where
  show (Bi dk dv)  = "BiDictionary(" ++ intercalate "," (aux (D.keysValues dk)) ++ ")"
                        ++ "(" ++ intercalate "," (aux (D.keysValues dv)) ++ ")"
   where
    aux kvs  = map (\(k,v) -> show k ++ "->" ++ show v) kvs
