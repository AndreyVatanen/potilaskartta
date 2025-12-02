import Box from '@mui/material/Box';
import { DataGrid } from '@mui/x-data-grid';

export default function Potilaslista({ rows, onSelect }) {
    const columns = [
        { field: 'etunimi', headerName: 'Etunimi', width: 70 },
        { field: 'sukunimi', headerName: 'Sukunimi', width: 85 },
        { field: 'ika', headerName: 'Ikä', width: 35 },
        { field: 'id', headerName: 'id', width: 35 },
    ];

    return (
        <Box sx={{ height: 170, width: 275, overflow: 'hidden' }}>
            <DataGrid
                rows={rows}
                columns={columns}
                hideFooter
                density="compact"
                disableMultipleRowSelection
                onRowSelectionModelChange={(ids) => {
                    const selectedId = ids[0];
                    const potilas = rows.find(r => r.id === selectedId);
                    onSelect?.(potilas);
                }}
            />
        </Box>
    );
}
