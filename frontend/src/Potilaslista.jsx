import Box from '@mui/material/Box';
import { DataGrid } from '@mui/x-data-grid';

export default function Potilaslista({ rows, title }) {

    const columns = [
        { field: 'etunimi', headerName: 'Etunimi', width: 70 },
        { field: 'sukunimi', headerName: 'Sukunimi', width: 85 },
        { field: 'ika', headerName: 'Ikä', width: 35 },
    ];

    return (
        <Box
            sx={{
                height: 170,
                width: 275,
                overflow: 'hidden',
            }}
        >
            <DataGrid
                rows={rows}
                columns={columns}
                hideFooter
                checkboxSelection
                disableRowSelectionOnClick
                disableMultipleRowSelection

                rowHeight={26}
                headerHeight={26}

                sx={{
                    fontSize: "0.75rem",

                    /* Pienempi checkbox ilman transformia (ei riko layoutia) */
                    "& .MuiCheckbox-root": {
                        padding: 0,
                        width: "18px",
                        height: "18px",
                    },

                    /* Checkbox-sarakkeen leveys kuntoon */
                    "& .MuiDataGrid-columnHeaderCheckbox, & .MuiDataGrid-cellCheckbox": {
                        width: "30px !important",
                        maxWidth: "30px !important",
                        minWidth: "30px !important",
                    },

                    /* Headerin korkeus */
                    "& .MuiDataGrid-columnHeaders": {
                        minHeight: "26px !important",
                        maxHeight: "26px !important",
                    },

                    /* Rivin korkeus */
                    "& .MuiDataGrid-row": {
                        maxHeight: "26px !important",
                        minHeight: "26px !important",
                    },
                }}
            />
        </Box>
    );
}
