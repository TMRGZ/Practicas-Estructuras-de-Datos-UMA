import DataStructures.Graph.DiGraph
import DataStructures.Graph.DiGraphBFT

data PosBarca = I | D deriving (Eq, Ord, Show)
data MC = E Int Int PosBarca deriving (Eq, Ord, Show)

legal m c = m>=0 && m<=3 && c>=0 && c<=3 && (m==0 || m>=c)  --Movimientos posibles

enBarca = [(m,c) | m <- [0..3], c <-[0..3], m+c>0, m+c<=2]  --Viajeros posibles

suc (E m c I) = [ E m' c' D | (bm,bc) <- enBarca, 
                                let m' = m-bm, 
                                let c' = c-bc,
                                legal m' c',
                                legal (3-m') (3-c')]

suc (E m c I) = [ E m' c' D | (bm,bc) <- enBarca, 
                                let m' = m+bm, 
                                let c' = c+bc,
                                legal m' c',
                                legal (3-m') (3-c')]