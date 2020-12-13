--Ejercicio 1)
weights :: Ord a => [a] -> D.Dictionary a Int
weights [] = D.empty
weights (x:xs) = mete x (weights xs)

mete x d
  | D.isDefinedAt x d = D.insert x (n+1) d
  | otherwise = D.insert x 1 d
  where Just n = D.valueOf x d

--Ejercicio 2.a)
huffmanLeaves :: String -> PQ.PQueue (WLeafTree Char)
huffmanLeaves s = foldr PQ.enqueue PQ.empty la
  where
    d = weights s
    lp = D.keysValues d
    la = map (\(k,v) -> WLeaf k v) lp --lista de árboles

--Ejercicio 2.b)
huffmanTree :: String -> WLeafTree Char
huffmanTree s
  | length (nub s) < 2 = error "POCOS DATOS"
  | otherwise = huffmanTreeAux (huffmanLeaves s)
  where
    huffmanTreeAux pq
      | isSingleton pq = PQ.first pq
      | otherwise = huffmanTreeAux (PQ.enqueue (merge w1 w2) pq2)
        where
          (w1, pq1) = firstAndDequeue pq
          (w2, pq2) = firstAndDequeue pq1

isSingleton pq = PQ.isEmpty (PQ.dequeue pq) --la cola tiene un solo elemento
firstAndDequeue pq = (PQ.first pq, PQ.dequeue pq) --la cola me da el primero y la cola sin el primero

--Ejercicio 3.a)
joinDics :: Ord a => D.Dictionary a b -> D.Dictionary a b -> D.Dictionary a b
joinDics d1 d2 = foldr (\(k,v) d -> D.insert k v d) d1 (D.keysValues d2)

--Ejercicio 3.b)
prefixWith :: Ord a => b -> D.Dictionary a [b] -> D.Dictionary a [b]
prefixWith = foldr (\(k,v) d -> D.insert k (b:v) d) D.empty (D.keysValues dic)

--Ejercicio 3.c)
huffmanCode :: WLeafTree Char -> D.Dictionary Char [Integer]
huffmanCode (WLeaf c _) = D.insert c [] D.empty
huffmanCode (WNode wl wr _) = joinDics (prefixWith 0 (huffmanCode wl)) (prefixWith 1 (huffmanCode wr))
