module ExSept2016 where
import qualified DataStructures.Dictionary.AVLDictionary as D
import qualified DataStructures.PriorityQueue.WBLeftistHeapPriorityQueue as PQ
import Data.List (nub)

weights :: Ord a => [a] -> D.Dictionary a Int
weights  = foldr (\e d -> D.updateOrInsert e (+1) 1 d) D.empty

huffmanLeaves :: String -> PQ.PQueue (WLeafTree Char)
huffmanLeaves s = foldr PQ.enqueue PQ.empty la
    where 
        d = weights s
        lp = D.keysValues d
        la = map (\(k,v) -> WLeaf k v) lp

huffmanTree :: String -> WLeafTree Char
huffmanTree s = huffmanTreeAux (huffmanLeaves s)
        where
            huffmanTreeAux pq
                | isSingleton pq = PQ.first pq
                | otherwise = huffmanTreeAux (PQ.enqueue (merge w1 w2) pq2)
                where
                    (w1, pq1) = firstAndDequeue pq
                    (w2, pq2) = firstAndDequeue pq1
            isSingleton pq = PQ.isEmty (PQ.dequeue pq)
            firstAndDequeue pq = (PQ.first pq, PQ.dequeu)


joinDics :: Ord a => D.Dictionary a b -> D.Dictionary a b -> D.Dictionary a b
joinDics d1 d2 = foldr (\(k,v) d -> D.insert k v d) d1 (D.keysValues d2)

prefixWith :: Ord a => b -> D.Dictionary a [b] -> D.Dictionary a [b]
prefixWith b dic = foldr (\(k,v) d -> D.insert k (b:v) d) D.empty (D.keysValues dic)

huffmanCode :: WleafTree Char -> D.Dictionary Char [Integer]
huffmanCode (WLeaf c _) = D.insert c [] D.empty
huffmanCode (WNode w1 wr _) joinDics (prefixWith 0 (huffmanCode))