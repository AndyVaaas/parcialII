import { Routes, Route } from 'react-router-dom';
import Proveedores from './pages/Proveedores';

function App() {
  return (
    <Routes>
      <Route path="/proveedores" element={<Proveedores />} />
      <Route path="/" element={<Proveedores />} />
    </Routes>
  );
}

export default App;

