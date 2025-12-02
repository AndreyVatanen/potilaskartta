import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import Potilaskartta from './potilaskartta.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <Potilaskartta />
  </StrictMode>
)
