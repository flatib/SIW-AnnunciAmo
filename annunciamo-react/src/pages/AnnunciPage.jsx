import { useEffect, useState } from "react";
import SearchBar from "../components/SearchBar";
import AnnuncioCard from "../components/AnnuncioCard";
import { searchAnnunci } from "../services/annuncioService";
import { getCategorie } from "../services/categoriaService";

function AnnunciPage() {
  const [annunci, setAnnunci] = useState([]);
  const [categorie, setCategorie] = useState([]);
  const [loading, setLoading] = useState(true);
  const [categorieLoading, setCategorieLoading] = useState(true);
  const [error, setError] = useState("");
  const [categorieError, setCategorieError] = useState("");

  async function loadAnnunci(filtri = {}) {
    try {
      setLoading(true);
      setError("");
      const dati = await searchAnnunci(filtri);
      setAnnunci(Array.isArray(dati) ? dati : []);
    } catch (err) {
      console.error("Errore nella ricerca annunci", err);
      setError("Non riesco a caricare gli annunci. Controlla che il backend sia avviato.");
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    let active = true;

    async function loadInitialData() {
      const [annunciResult, categorieResult] = await Promise.allSettled([
        searchAnnunci(),
        getCategorie(),
      ]);

      if (!active) return;

      if (annunciResult.status === "fulfilled") {
        setAnnunci(Array.isArray(annunciResult.value) ? annunciResult.value : []);
      } else {
        console.error("Errore nella ricerca annunci", annunciResult.reason);
        setError("Non riesco a caricare gli annunci. Controlla che il backend sia avviato.");
      }

      if (categorieResult.status === "fulfilled") {
        setCategorie(Array.isArray(categorieResult.value) ? categorieResult.value : []);
      } else {
        console.error("Errore nel caricamento categorie", categorieResult.reason);
        setCategorieError("Categorie non disponibili.");
      }

      setLoading(false);
      setCategorieLoading(false);
    }

    loadInitialData();

    return () => {
      active = false;
    };
  }, []);

  return (
    <div className="annunci-react-page">
      <SearchBar
        categorie={categorie}
        categorieError={categorieError}
        categorieLoading={categorieLoading}
        onSearch={loadAnnunci}
        onReset={() => loadAnnunci()}
        loading={loading}
      />

      <main className="annunci-react-content" aria-live="polite">
        <div className="annunci-react-content__header">
          <h2>Annunci disponibili</h2>
          <p>
            {loading
              ? "Aggiornamento risultati..."
              : `${annunci.length} ${annunci.length === 1 ? "risultato" : "risultati"}`}
          </p>
        </div>

        {error && <p className="react-alert react-alert--error">{error}</p>}

        {!loading && !error && annunci.length === 0 && (
          <p className="react-alert">Nessun annuncio trovato con questi filtri.</p>
        )}

        <div className="annunci-react-list">
          {annunci.map((annuncio) => (
            <AnnuncioCard key={annuncio.id} annuncio={annuncio} />
          ))}
        </div>
      </main>
    </div>
  );
}

export default AnnunciPage;
